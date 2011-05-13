package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Immutable;

public class UserInfo implements Immutable {

	private final String userinfo;
	private final String password;

	public static UserInfo UserInfo(String userInfo) {
		String userinfo = StringUtils.substringBefore(userInfo, ":");
		String passwordStr = StringUtils.substringAfter(userInfo, ":");
		return new UserInfo(userinfo, passwordStr.isEmpty() ? null : passwordStr);
	}

	private UserInfo(String userinfo, String password) {
		super();
		this.userinfo = userinfo;
		this.password = password;
	}

	public String getUserinfo() {
		return userinfo;
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
		return userinfo + (password == null ? "" : ":" + password);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserInfo userInfo = (UserInfo) o;

		if (password != null ? !password.equals(userInfo.password) : userInfo.password != null) return false;
		if (!userinfo.equals(userInfo.userinfo)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userinfo.hashCode();
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
}
