package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public class Path extends WrappedString {

	public static Path Path(String path) {
		return new Path(path);
	}

	private Path(String wrappedValue) {
		super(wrappedValue);
	}

	@Override public Path toImmutable() {
		return this;
	}
}
