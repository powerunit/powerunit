package ch.powerunit.impl;

import java.util.function.Supplier;

import ch.powerunit.AssertThatString;

public class AssertThatStringImpl extends AssertThatObjectImpl<String>
        implements AssertThatString {

    public AssertThatStringImpl(Object underTest,boolean assertion, String msg,
            Supplier<String> provider) {
        super(underTest,assertion, msg, provider);
    }

}
