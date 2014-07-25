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
package ch.powerunit;

import java.io.PrintStream;
import java.util.Arrays;

import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.impl.DefaultTestResultListener;

/**
 * Main class that can be used to run test.
 * 
 * @author borettim
 * @since 0.1.0
 * @see #main(String[]) The main method to have the description of the
 *      parameters.
 */
public class PowerUnitMainRunner {

    public static final PrintStream DEFAULT_OUT = System.out;

    /**
     * The main method.
     * 
     * @param args
     *            The argument syntax is the following :
     *            <ul>
     *            <li><code>path className,...</code></li>
     *            </ul>
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            DEFAULT_OUT.printf("The received argument is not valid : %1$s%n",
                    Arrays.toString(args));
            System.exit(-1);
        }
        String outputPath = args[0];
        String classes[] = args[1].split("\\s*,\\s*");
        boolean success = true;
        for (String c : classes) {
            DEFAULT_OUT.printf("Running test for %1$s%n", c);
            try {
                Class<?> cls = Class.forName(c);
                PowerUnitRunner r = new DefaultPowerUnitRunnerImpl(cls);
                DefaultTestResultListener def = new DefaultTestResultListener(
                        outputPath, DEFAULT_OUT);
                r.addListener(def);
                r.run();
                success &= !def.isError();
            } catch (ClassNotFoundException e) {
                DEFAULT_OUT.printf("Unable to create the class %1$s%n", c);
            } finally {
                DEFAULT_OUT.printf("End running test for %1$s%n", c);
            }
        }
        if (!success) {
            System.exit(-1);
        }
        System.exit(0);
    }

}
