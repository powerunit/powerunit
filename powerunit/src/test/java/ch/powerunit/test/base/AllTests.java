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
package ch.powerunit.test.base;

public class AllTests {
    public static void main(String[] args) {
        PowerUnitProviderListenerTests.main(args);
        PowerUnitProviderScannerFilterTests.main(args);
        MockitoRuleTests.main(args);
        TestContextRuleTests.main(args);
        ExternalResourceTests.main(args);
        RuntimeParametersValidatorTests.main(args);
        RuntimeRuleValidatorTests.main(args);
        RuntimeTestValidatorTests.main(args);
        PowerUnitProviderTests.main(args);
        SystemPropertiesRuleTests.main(args);
        TemporaryFolderTests.main(args);
        StreamParametersMapFunctionTests.main(args);
        MatchersTests.main(args);
        FileMatchersTests.main(args);
        TestDelegateTests.main(args);
        SystemStreamRuleTests.main(args);
    }

}
