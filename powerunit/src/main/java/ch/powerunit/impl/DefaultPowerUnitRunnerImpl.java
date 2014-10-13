/**
 * Powerunit - A JDK1.8 test framework
 * Copyright (C) 2014 Mathieu Boretti.
 *
 * This file is part of Powerunit
 *
 * Powerunit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Powerunit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Powerunit. If not, see <http://www.gnu.org/licenses/>.
 */
package ch.powerunit.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Ignore;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.PowerUnitRunner;
import ch.powerunit.Rule;
import ch.powerunit.Statement;
import ch.powerunit.Test;
import ch.powerunit.TestContext;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestInterface;
import ch.powerunit.TestResultListener;
import ch.powerunit.TestRule;
import ch.powerunit.exception.AssumptionError;
import ch.powerunit.exception.InternalError;
import ch.powerunit.impl.validator.ParameterValidator;
import ch.powerunit.impl.validator.ParametersValidator;
import ch.powerunit.impl.validator.RuleValidator;
import ch.powerunit.impl.validator.TestDelegateValidator;
import ch.powerunit.impl.validator.TestValidator;

public class DefaultPowerUnitRunnerImpl<T> implements PowerUnitRunner<T>,
        ParametersValidator, ParameterValidator, TestValidator, RuleValidator,
        TestDelegateValidator {

    private final List<TestResultListener<Object>> listeners = new ArrayList<>();

    private final String parentGroups;

    private final T targetObject;

    private final String setName;

    private final Method singleMethod;

    private final Object externalParameter;

    DefaultPowerUnitRunnerImpl(Class<T> testClass, Object externalParameter) {
        this(testClass, null, externalParameter);
    }

    public DefaultPowerUnitRunnerImpl(Class<T> testClass) {
        this(testClass, null);
    }

    public DefaultPowerUnitRunnerImpl(Class<T> testClass, Method singleMethod) {
        this(testClass, singleMethod, null);
    }

    DefaultPowerUnitRunnerImpl(Class<T> testClass, Method singleMethod,
            Object externalParameter) {
        Objects.requireNonNull(testClass);

        this.singleMethod = singleMethod;
        this.setName = testClass.getName();
        this.externalParameter = externalParameter;
        Set<String> groups = findClass(testClass)
                .stream()
                .filter(c -> c.isAnnotationPresent(Categories.class))
                .map(c -> Arrays.stream(
                        c.getAnnotation(Categories.class).value()).collect(
                        Collectors.toCollection(() -> new HashSet<>())))
                .reduce((o, n) -> {
                    o.addAll(n);
                    return o;
                }).orElse(new HashSet<>());
        this.parentGroups = groups.isEmpty() ? TestResultListener.ALL_GROUPS
                : Arrays.toString(groups.toArray());

        try {
            targetObject = testClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InternalError("Unexpected error " + e.getMessage(), e);
        }

        if (testClass.isAnnotationPresent(Ignore.class)) {
            executableTests.put(setName, p -> {
                TestContextImpl<Object> ctx = new TestContextImpl<>(
                        targetObject, setName, setName, null, parentGroups);
                notifyStartTest(ctx);
                notifyEndSkippedTest(ctx);
            });
            return;
        }
        findTestsMethod(targetObject, testClass, parentGroups);
        findTestsRule(targetObject, testClass);
        findParametersMethod(targetObject, testClass);
        findDelegateTest(targetObject, testClass);
        computeExecutableStatements();
        computeDelegateStatements();
    }

    @Override
    public void run() {
        notifyStartTests(setName, parentGroups);
        try {
            if (parameters != null) {
                runAll();
            } else {
                runOne(null);
            }
        } finally {
            notifyEndTests(setName, parentGroups);
        }

    }

    private void runAll() {
        testIndex = 0;
        try (Stream<?> params = (Stream<?>) (externalParameter == null ? parameters
                .invoke(targetObject) : parameters.invoke(targetObject,
                externalParameter))) {
            params.forEach(this::runOneParameter);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new InternalError("Unexpected error " + e.getMessage(), e);
        }
    }

    private int testIndex = 0;

    private void runOneParameter(Object op) {
        String formatter = parameters.getAnnotation(Parameters.class).value();
        if ("".equals(formatter)) {
            formatter = "" + testIndex;
        }
        Object o[];
        if (op != null && op.getClass().isArray()) {
            o = (Object[]) op;
        } else {
            o = new Object[] { op };
        }
        String name = MessageFormat.format(formatter, o);
        try {
            notifyStartParameter(setName, name);
            int pidx = 0;
            if (o.length != parameterFields.size()) {
                throw new InternalError(
                        "Parameter fields count doesn't match with array size returned by parameters");
            }
            for (Object p : o) {
                try {
                    Field f = parameterFields.get(pidx);
                    if (f == null) {
                        throw new InternalError("Field " + pidx
                                + " is not found");
                    }
                    f.set(targetObject, p);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new InternalError("Unexpected error "
                            + e.getMessage(), e);
                }
                pidx++;
            }
            runOne(name, o);
            testIndex++;
        } finally {
            notifyEndParameter(setName, name);
        }
    }

    @SuppressWarnings("unchecked")
    private void runOne(String name, Object... parameters) {
        executableTests
                .entrySet()
                .forEach(
                        singleTest -> {
                            try {
                                boolean run = true;
                                String tname = singleTest.getKey();
                                if (filterParameterField != null) {
                                    run = ((BiFunction<String, Object, Boolean>) filterParameterField
                                            .get(targetObject)).apply(
                                            testMethods.get(tname).getName(),
                                            parameters);
                                }
                                if (run) {
                                    if (parameters.length > 0) {
                                        tname = MessageFormat.format(tname,
                                                parameters);
                                    }
                                    singleTest.getValue().run(
                                            new TestContextImpl<Object>(
                                                    targetObject, setName,
                                                    tname, name, parentGroups));
                                }
                            } catch (Throwable e) {// NOSONAR
                                // As we really want all error
                                throw new InternalError("Unexpected error "
                                        + e.getMessage(), e);
                            }
                        });
        delegateTests
                .entrySet()
                .forEach(
                        singleTest -> {
                            try {
                                boolean run = true;
                                String tname = singleTest.getKey();
                                if (filterParameterField != null) {
                                    run = ((BiFunction<String, Object, Boolean>) filterParameterField
                                            .get(targetObject)).apply(tname,
                                            parameters);
                                }
                                if (run) {
                                    if (parameters.length > 0) {
                                        tname = MessageFormat.format(tname,
                                                parameters);
                                    }
                                    singleTest.getValue().run(
                                            new TestContextImpl<Object>(
                                                    targetObject, setName,
                                                    tname, name, parentGroups));
                                }
                            } catch (Throwable e) {// NOSONAR
                                // As we really want all error
                                throw new InternalError("Unexpected error "
                                        + e.getMessage(), e);
                            }
                        });
    }

    private final Map<String, Method> testMethods = new HashMap<>();

    private TestRule testRules = null;

    private Map<String, Statement<TestContext<Object>, Throwable>> executableTests = new HashMap<>();

    private Map<String, Statement<TestContext<Object>, Throwable>> delegateTests = new HashMap<>();

    private Method parameters = null;

    private Map<Integer, Field> parameterFields;

    private Field filterParameterField = null;

    private Map<String, Supplier<Object>> delegateTest = new HashMap<>();

    private void findDelegateTest(T targetObject, Class<T> testClass) {
        findClass(testClass)
                .stream()
                .forEach(
                        cls -> Arrays
                                .stream(cls.getDeclaredFields())
                                .filter(f -> f
                                        .isAnnotationPresent(TestDelegate.class))
                                .forEach(
                                        f -> {
                                            checkTestDelegateAnnotationForField(f);
                                            if (Supplier.class
                                                    .isAssignableFrom(f
                                                            .getType())) {
                                                try {
                                                    delegateTest.put(
                                                            f.getName(),
                                                            (Supplier<Object>) f
                                                                    .get(targetObject));
                                                    return;
                                                } catch (
                                                        IllegalAccessException
                                                        | IllegalArgumentException e) {
                                                    throw new InternalError(
                                                            "Unexpected error "
                                                                    + e.getMessage(),
                                                            e);
                                                }
                                            }
                                            delegateTest.put(
                                                    f.getName(),
                                                    (Supplier<Object>) () -> {
                                                        try {
                                                            return f.get(targetObject);
                                                        } catch (
                                                                IllegalAccessException
                                                                | IllegalArgumentException e) {
                                                            throw new InternalError(
                                                                    "Unexpected error "
                                                                            + e.getMessage(),
                                                                    e);
                                                        }
                                                    });
                                        }));

    }

    private void findParametersMethod(T targetObject, Class<T> testClass) {
        parameters = Arrays
                .stream(testClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Parameters.class))
                .peek(m -> checkParametersAnnotationForMethod(m))
                .reduce((o, n) -> {
                    throw new InternalError(
                            "@Parameters method can't only be once");
                }).orElse(null);
        parameterFields = Arrays
                .stream(testClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Parameter.class))
                .peek(f -> {
                    if (parameters == null) {
                        throw new InternalError(
                                "@Parameter can't be used without @Parameters method");
                    }
                })
                .peek(f -> checkParameterAnnotationForField(f))
                .collect(
                        Collectors
                                .<Field, Integer, Field> toMap(
                                        (Field f) -> f.getAnnotation(
                                                Parameter.class).value(),
                                        (Field f) -> f,
                                        (f1, f2) -> {
                                            throw new InternalError(
                                                    "@Parameter can't be used twice with the same value number");
                                        }));
        if (parameters != null) {
            // assuming field numbering 0 to
            int size = parameterFields.size();
            if (size == 0) {
                throw new InternalError("No @Parameter field found");
            }
            int expected = size * (size - 1) / 2;
            int sum = parameterFields.keySet().stream().mapToInt(i -> i).sum();
            if (sum != expected) {
                throw new InternalError(
                        "@Parameter field number aren't continuus");
            }
            parameterFields
                    .values()
                    .stream()
                    .forEach(
                            f -> {
                                Parameter p = f.getAnnotation(Parameter.class);
                                if (p.filter()) {
                                    if (filterParameterField != null) {
                                        throw new InternalError(
                                                "@Parameter filter attribute can only be used once per test class.");
                                    }
                                    if (!BiFunction.class.isAssignableFrom(f
                                            .getType())) {
                                        throw new InternalError(
                                                "@Parameter filter attribute can only be use on BiFunction.");
                                    }
                                    filterParameterField = f;

                                }
                            });
        }
    }

    private void findTestsMethod(T targetObject, Class<T> testClass,
            String parentGroup) {
        findClass(testClass).forEach(
                cls -> {
                    Arrays.stream(cls.getDeclaredMethods())
                            .filter(m -> m.isAnnotationPresent(Test.class))
                            .filter(m -> singleMethod == null
                                    || singleMethod.equals(m)).forEach(m -> {
                                checkTestAnnotationForMethod(m);
                                Test annotation = m.getAnnotation(Test.class);
                                String testName = m.getName();
                                if (!"".equals(annotation.name())) {
                                    testName = annotation.name();
                                }
                                testMethods.put(testName, m);
                            });
                });
    }

    private void findTestsRule(T targetObject, Class<T> testClass) {
        testRules = findClass(testClass)
                .stream()
                .map(cls -> Arrays
                        .stream(cls.getDeclaredFields())
                        .filter(f -> f.isAnnotationPresent(Rule.class))
                        .map(f -> {
                            checkRuleAnnotationForField(f);
                            try {
                                TestRule tr1 = (TestRule) f.get(targetObject);
                                if (tr1 == null) {
                                    throw new InternalError(
                                            "@Rule annotation is used on a null field. This is not allowed");
                                }
                                return tr1;
                            } catch (IllegalAccessException
                                    | IllegalArgumentException e) {
                                throw new InternalError("Unexpected error "
                                        + e.getMessage(), e);
                            }
                        })
                        .reduce((o, n) -> {
                            throw new InternalError(
                                    "@Rule annotation can only be used once on field");
                        }).orElse(null)).filter(i -> i != null)
                .reduce((o, n) -> o.around(n)).orElse(null);

    }

    private List<Class<?>> findClass(Class<T> testClass) {
        List<Class<?>> clazzs = new ArrayList<>();
        Class<?> current = testClass;
        while (current != null) {
            clazzs.add(0, current);
            current = current.getSuperclass();
        }
        return clazzs;
    }

    private void computeExecutableStatements() {
        executableTests = testMethods
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                test -> test.getKey(),
                                test -> {
                                    Statement<TestContext<Object>, Throwable> stest;
                                    if (test.getValue().isAnnotationPresent(
                                            Ignore.class)) {
                                        stest = p -> {
                                            throw new AssumptionError(
                                                    "Test method is annotated with @Ignore");
                                        };
                                    } else {
                                        Statement<TestContext<Object>, Throwable> itest = p -> {
                                            Statement
                                                    .<TestContext<Object>, Throwable> reflectionMethod(
                                                            targetObject,
                                                            test.getValue())
                                                    .run(p);
                                        };
                                        if (testRules != null) {
                                            stest = p -> testRules
                                                    .computeStatement(itest)
                                                    .run(p);
                                        } else {
                                            stest = itest;
                                        }

                                    }
                                    return p -> {
                                        notifyStartTest(p);
                                        try {
                                            stest.run(p);
                                            notifyEndSuccessTest(p);
                                        } catch (InternalError e) {
                                            notifyEndFailureTest(p, e);
                                        } catch (AssertionError e) {
                                            notifyEndFailureTest(p, e);
                                        } catch (AssumptionError e) {
                                            notifyEndSkippedTest(p);
                                        } catch (Throwable e) {// NOSONAR
                                            // As we really want all error
                                            notifyEndFailureTest(p, e);
                                        }
                                    };
                                }));
    }

    private void computeDelegateStatements() {
        delegateTests = delegateTest.entrySet().stream()
                .collect(Collectors.toMap(test -> test.getKey(), test -> {
                    Statement<TestContext<Object>, Throwable> stest;
                    Statement<TestContext<Object>, Throwable> itest = p -> {
                        new Statement<TestContext<Object>, Throwable>() {

                            @Override
                            public void run(TestContext<Object> parameter)
                                    throws Throwable {
                                Supplier<Object> o = test.getValue();
                                Object target = o.get();
                                Class<?> delegator = target.getClass()
                                        .getAnnotation(TestInterface.class)
                                        .value();
                                DefaultPowerUnitRunnerImpl<?> dpu = new DefaultPowerUnitRunnerImpl<>(
                                        delegator, target);
                                dpu.addListener((TestResultListener) new DelegationTestResultListener(
                                        parameter));
                                dpu.run();
                            }

                            @Override
                            public String getName() {
                                return test.getKey();
                            }
                        }.run(p);
                    };
                    if (testRules != null) {
                        stest = p -> testRules.computeStatement(itest).run(p);
                    } else {
                        stest = itest;
                    }

                    return p -> {
                        try {
                            stest.run(p);
                        } catch (InternalError e) {
                            notifyStartTest(p);
                            notifyEndFailureTest(p, e);
                        } catch (AssertionError e) {
                            notifyStartTest(p);
                            notifyEndFailureTest(p, e);
                        } catch (AssumptionError e) {
                            notifyStartTest(p);
                            notifyEndSkippedTest(p);
                        } catch (Throwable e) {// NOSONAR
                            // As we really want all error
                            notifyStartTest(p);
                            notifyEndFailureTest(p, e);
                        }
                    };
                }));
    }

    @Override
    public void addListener(TestResultListener<T> listener) {
        listeners.add((TestResultListener) listener);
    }

    private void notifyStartTests(String setName, String groups) {
        listeners.forEach(trl -> trl.notifySetStart(setName, groups));
    }

    private void notifyEndTests(String setName, String groups) {
        listeners.forEach(trl -> trl.notifySetEnd(setName, groups));
    }

    private void notifyStartParameter(String setName, String parameterName) {
        listeners.forEach(trl -> trl.notifyParameterStart(setName,
                parameterName));
    }

    private void notifyEndParameter(String setName, String parameterName) {
        listeners
                .forEach(trl -> trl.notifyParameterEnd(setName, parameterName));
    }

    private void notifyStartTest(TestContext<Object> context) {
        listeners.forEach(trl -> trl.notifyStart(context));
    }

    private void notifyEndSuccessTest(TestContext<Object> context) {
        listeners.forEach(trl -> trl.notifySuccess(context));
    }

    private void notifyEndSkippedTest(TestContext<Object> context) {
        listeners.forEach(trl -> trl.notifySkipped(context));
    }

    private void notifyEndFailureTest(TestContext<Object> context,
            AssertionError cause) {
        listeners.forEach(trl -> trl.notifyFailure(context, cause));
    }

    private void notifyEndFailureTest(TestContext<Object> context,
            InternalError cause) {
        listeners.forEach(trl -> trl.notifyError(context, cause));
    }

    private void notifyEndFailureTest(TestContext<Object> context,
            Throwable cause) {
        listeners.forEach(trl -> trl.notifyError(context, cause));
    }

    private class DelegationTestResultListener implements
            TestResultListener<Object> {

        private final TestContext<Object> parentContext;

        public DelegationTestResultListener(TestContext<Object> parentContext) {
            this.parentContext = parentContext;
        }

        @Override
        public void notifySetStart(String setName, String groups) {
            // ignore
        }

        @Override
        public void notifySetEnd(String setName, String groups) {
            // ignore
        }

        @Override
        public void notifyStart(TestContext<Object> context) {
            TestContext<Object> ctx = new TestContextImpl<Object>(
                    parentContext.getTestSuiteObject(),
                    parentContext.getSetName(), context.getLocalTestName(),
                    parentContext.getParameterName(),
                    parentContext.getTestCategories());
            notifyStartTest(ctx);
        }

        @Override
        public void notifySuccess(TestContext<Object> context) {
            TestContext<Object> ctx = new TestContextImpl<Object>(
                    parentContext.getTestSuiteObject(),
                    parentContext.getSetName(), context.getLocalTestName(),
                    parentContext.getParameterName(),
                    parentContext.getTestCategories());
            notifyEndSuccessTest(ctx);
        }

        @Override
        public void notifyFailure(TestContext<Object> context, Throwable cause) {
            TestContext<Object> ctx = new TestContextImpl<Object>(
                    parentContext.getTestSuiteObject(),
                    parentContext.getSetName(), context.getLocalTestName(),
                    parentContext.getParameterName(),
                    parentContext.getTestCategories());
            notifyEndFailureTest(ctx, (AssertionError) cause);
        }

        @Override
        public void notifyError(TestContext<Object> context, Throwable cause) {
            TestContext<Object> ctx = new TestContextImpl<Object>(
                    parentContext.getTestSuiteObject(),
                    parentContext.getSetName(), context.getLocalTestName(),
                    parentContext.getParameterName(),
                    parentContext.getTestCategories());
            notifyEndFailureTest(ctx, cause);
        }

        @Override
        public void notifySkipped(TestContext<Object> context) {
            TestContext<Object> ctx = new TestContextImpl<Object>(
                    parentContext.getTestSuiteObject(),
                    parentContext.getSetName(), context.getLocalTestName(),
                    parentContext.getParameterName(),
                    parentContext.getTestCategories());
            notifyEndSkippedTest(ctx);
        }

        @Override
        public void notifyParameterStart(String setName, String parameterName) {
            // ignore
        }

        @Override
        public void notifyParameterEnd(String setName, String parameterName) {
            // ignore
        }

    }
}
