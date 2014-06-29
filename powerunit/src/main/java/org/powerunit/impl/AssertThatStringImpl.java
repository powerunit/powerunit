package org.powerunit.impl;

import java.util.function.Supplier;

import org.powerunit.AssertThatString;

public class AssertThatStringImpl extends AssertThatObjectImpl<String>
		implements AssertThatString {

	public AssertThatStringImpl(boolean assertion, String msg,
			Supplier<String> provider) {
		super(assertion, msg, provider);
	}

}