package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public class Password extends WrappedString {
	
	public static Password Password(String password) {
		return new Password(password);
	}

	private Password(String wrappedValue) {
		super(wrappedValue);
	}

	@Override public Password toImmutable() {
		return this;
	}
}
