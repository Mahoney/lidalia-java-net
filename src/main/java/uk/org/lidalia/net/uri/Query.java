package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public class Query extends WrappedString<Query> {

    public static Query Query(String query) {
        return new Query(query);
    }

    private Query(String wrappedValue) {
        super(wrappedValue);
    }
}
