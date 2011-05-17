package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.RichRuntimeException;

public class IllegalUserIdException extends RichRuntimeException {

	private static final long serialVersionUID = 1L;

	private final String userIdString;

	public IllegalUserIdException(String userIdString) {
		this(userIdString, null);
	}

	public IllegalUserIdException(String userIdString, Throwable cause) {
		super("[" + userIdString + "] is not a valid UserId - must match " + UserId.LEGAL_CHARS_STRING, cause);
		this.userIdString = userIdString;
	}

	public String getUserIdString() {
		return userIdString;
	}

}
