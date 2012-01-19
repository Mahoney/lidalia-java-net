package uk.org.lidalia.net;

import uk.org.lidalia.lang.WrappedString;

public class Host extends WrappedString {

	public static Host Host(String hostStr) {
		return new Host(hostStr);
	}

	Host(String wrappedValue) {
		super(wrappedValue);
	}
}
