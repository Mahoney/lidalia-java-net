package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public class Fragment extends WrappedString {

	public static Fragment Fragment(String fragment) {
		return new Fragment(fragment);
	}

	private Fragment(String wrappedValue) {
		super(wrappedValue);
	}

	@Override public Fragment toImmutable() {
		return this;
	}
}
