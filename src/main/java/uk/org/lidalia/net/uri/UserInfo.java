package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;

import static uk.org.lidalia.net.uri.UserId.UserId;

public class UserInfo extends RichObject implements Immutable {

	@Identity private final UserId userId;
	@Identity private final String password;

	public static UserInfo UserInfo(String userInfo) {
		UserId userId = UserId(StringUtils.substringBefore(userInfo, ":"));
		String passwordStr = StringUtils.substringAfter(userInfo, ":");
		return new UserInfo(userId, passwordStr.isEmpty() ? null : passwordStr);
	}

	private UserInfo(UserId userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public UserId getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public UserInfo toImmutable() {
		return this;
	}

	@Override
	public String toString() {
		return userId + (password == null ? "" : ":" + password);
	}
}
