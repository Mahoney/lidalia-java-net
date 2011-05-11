package uk.org.lidalia.net.uri;

import uk.org.lidalia.lang.WrappedString;

public class UserInfo extends WrappedString {

	public static UserInfo UserInfo(String userInfo) {
		return new UserInfo(userInfo);
	}

	private UserInfo(String wrappedValue) {
		super(wrappedValue);
	}
}
