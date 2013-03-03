package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public final class Path extends WrappedString<Path> {

    public static Path Path(String path) {
        return new Path(path);
    }

    private Path(String wrappedValue) {
        super(wrappedValue);
    }
}
