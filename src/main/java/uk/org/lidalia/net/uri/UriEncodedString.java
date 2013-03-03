package uk.org.lidalia.net.uri;

import org.apache.commons.lang3.Validate;

import uk.org.lidalia.lang.WrappedString;

public class UriEncodedString extends WrappedString {

    private static final String UNRESERVED = "a-zA-Z0-9\\-\\._~";
    private static final String SUB_DELIMS = "!\\$&'\\(\\)\\*\\+,;=";
    private static final String UNRESERVED_PLUS_SUB_DELIMS = "[" + UNRESERVED + SUB_DELIMS + "]";
    private static final String HEX_DIGIT = "(%[0-9a-fA-F][0-9a-fA-F])";
    private static final String LEGAL_CHARS_STRING = "(" + UNRESERVED_PLUS_SUB_DELIMS + "|" + HEX_DIGIT + ")*";

    public static UriEncodedString UriEncodedString(String text) {
        Validate.matchesPattern(text, LEGAL_CHARS_STRING);
        return new UriEncodedString(text);
    }

    public static UriEncodedString encode(String text) {
        if (text.matches(UNRESERVED_PLUS_SUB_DELIMS + "*")) {
            return new UriEncodedString(text);
        } else {
            throw new UnsupportedOperationException(); // TODO implement...
        }
    }

    private UriEncodedString(String wrappedValue) {
        super(wrappedValue);
    }
    
    public String decode() {
        throw new UnsupportedOperationException("todo");
    }
}
