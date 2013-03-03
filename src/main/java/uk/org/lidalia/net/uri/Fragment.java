package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public class Fragment extends WrappedString<Fragment> {

    public static Fragment Fragment(String fragment) {
        return new Fragment(fragment);
    }

    private Fragment(String wrappedValue) {
        super(wrappedValue);
    }
}
