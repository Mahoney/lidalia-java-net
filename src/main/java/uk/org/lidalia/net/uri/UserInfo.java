package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;

import static com.google.common.base.Optional.of;
import static uk.org.lidalia.net.uri.Password.Password;
import static uk.org.lidalia.net.uri.UserId.UserId;

public class UserInfo extends RichObject implements Immutable {

	public static UserInfo UserInfo(String userInfo) {
		UserId userId = UserId(StringUtils.substringBefore(userInfo, ":"));
		String passwordStr = StringUtils.substringAfter(userInfo, ":");
		return new UserInfo(userId, passwordStr.isEmpty() ? Optional.<Password>absent() : of(Password(passwordStr)));
	}
	
	@Identity private final UserId userId;
	@Identity private final Optional<Password> password;

	private UserInfo(UserId userId, Optional<Password> password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public UserId getUserId() {
		return userId;
	}

	public Optional<Password> getPassword() {
		return password;
	}

	@Override
	public UserInfo toImmutable() {
		return this;
	}

	@Override
	public String toString() {
		return userId + (password.isPresent() ? ":" + password.get() : "");
	}
}
