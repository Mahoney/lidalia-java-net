package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

import java.util.regex.Pattern;

public class UserId extends WrappedString {

	public static final String LEGAL_CHARS_STRING = "[a-zA-Z0-9!\\$&'\\(\\)\\*\\+\\-\\._~,;=]*";
	private static final Pattern LEGAL_CHARS = Pattern.compile(LEGAL_CHARS_STRING);

	public static UserId UserId(String userId) {
		return new UserId(userId);
	}

	private UserId(String userId) {
		super(userId);
		if (!LEGAL_CHARS.matcher(userId).matches()) {
			throw new IllegalUserIdException(userId);
		}
	}

	@Override public UserId toImmutable() {
		return this;
	}
}
