package uk.org.lidalia.net.uri;

import org.apache.commons.lang3.Validate;

import uk.org.lidalia.lang.WrappedString;

public class UriText extends WrappedString {
	
	private static final String UNRESERVED = "a-zA-Z0-9\\-\\._~";
	private static final String SUB_DELIMS = "!\\$&'\\(\\)\\*\\+,;=";
	private static final String UNRESERVED_PLUS_SUB_DELIMS = "[" + UNRESERVED + SUB_DELIMS + "]";
	private static final String HEX_DIGIT = "(%[0-9a-fA-F][0-9a-fA-F])";
	private static final String LEGAL_CHARS_STRING = "(" + UNRESERVED_PLUS_SUB_DELIMS + "|" + HEX_DIGIT + ")*";
	
	public static UriText UriText(String text) {
		Validate.matchesPattern(text, LEGAL_CHARS_STRING);
		return new UriText(text);
	}

	public static UriText encode(String text) {
		if (text.matches(UNRESERVED_PLUS_SUB_DELIMS + "*")) {
			return new UriText(text);
		} else {
			throw new UnsupportedOperationException(); // TODO implement...
		}
	}

	UriText(String wrappedValue) {
		super(wrappedValue);
	}
}
