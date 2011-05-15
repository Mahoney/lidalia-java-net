package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

import static uk.org.lidalia.net.uri.UserInfo.UserInfo;
import static uk.org.lidalia.net.uri.HostAndPort.HostAndPort;

public class Authority implements Immutable {

	public static Authority Authority(String authority) {
		String userInfoStr;
		String hostAndPortStr;
		if (authority.contains("@")) {
			userInfoStr = StringUtils.substringBefore(authority, "@");
			hostAndPortStr = StringUtils.substringAfter(authority, "@");
		} else {
			userInfoStr = null;
			hostAndPortStr = authority;
		}
		UserInfo userInfo = (userInfoStr == null) ? null : UserInfo(userInfoStr);
		HostAndPort hostAndPort = HostAndPort.HostAndPort(hostAndPortStr);
		return Authority(userInfo, hostAndPort);
	}

	public static Authority Authority(Host host) {
		return Authority(host, null);
	}

	public static Authority Authority(UserInfo userInfo, Host host) {
		return Authority(userInfo, host, null);
	}

	public static Authority Authority(Host host, Port port) {
		return Authority(null, host, port);
	}

	public static Authority Authority(UserInfo userInfo, Host host, Port port) {
		return Authority(userInfo, HostAndPort(host, port));
	}

	public static Authority Authority(HostAndPort hostAndPort) {
		return Authority(null, hostAndPort);
	}

	public static Authority Authority(UserInfo userInfo, HostAndPort hostAndPort) {
		return new Authority(userInfo, hostAndPort);
	}

	private final UserInfo userInfo;
	private final HostAndPort hostAndPort;

	Authority(UserInfo userInfo, HostAndPort hostAndPort) {
		super();
		this.userInfo = userInfo;
		this.hostAndPort = hostAndPort;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public HostAndPort getHostAndPort() {
		return hostAndPort;
	}

	public Host getHost() {
		return hostAndPort.getHost();
	}

	public Port getPort() {
		return hostAndPort.getPort();
	}

	@Override public Authority toImmutable() {
		return this;
	}

	@Override public String toString() {
		String hostAndPortStr = hostAndPort.toString();
		return toString(hostAndPortStr);
	}

	public String toString(Scheme scheme) {
		String hostAndPortStr = hostAndPort.toString(scheme);
		return toString(hostAndPortStr);
	}

	private String toString(String hostAndPortStr) {
		return (userInfo == null) ? hostAndPortStr : userInfo + "@" + hostAndPort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Authority authority = (Authority) o;

		if (userInfo != null ? !userInfo.equals(authority.userInfo) : authority.userInfo != null) return false;
		if (!hostAndPort.equals(authority.hostAndPort)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userInfo != null ? userInfo.hashCode() : 0;
		result = 31 * result + hostAndPort.hashCode();
		return result;
	}
}
