package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

import static uk.org.lidalia.net.Host.Host;
import static uk.org.lidalia.net.Port.Port;
import static uk.org.lidalia.net.uri.UserInfo.UserInfo;

public class Authority implements Immutable {

	public static Authority Authority(String authority) {
		String userInfoStr;
		String hostAndPort;
		if (authority.contains("@")) {
			userInfoStr = StringUtils.substringBefore(authority, "@");
			hostAndPort = StringUtils.substringAfter(authority, "@");
		} else {
			userInfoStr = null;
			hostAndPort = authority;
		}
		UserInfo userInfo = (userInfoStr == null) ? null : UserInfo(userInfoStr);
		String portStr = StringUtils.substringAfterLast(hostAndPort, ":");
		Port port = (portStr.isEmpty()) ? null : Port(portStr);
		String hostStr = StringUtils.substringBeforeLast(hostAndPort, ":");
		Host host = Host(hostStr);
		return new Authority(userInfo, host, port);
	}

	public static Authority Authority(Host host) {
		return new Authority(null, host, null);
	}

	public static Authority Authority(UserInfo userInfo, Host host) {
		return new Authority(userInfo, host, null);
	}

	public static Authority Authority(Host host, Port port) {
		return new Authority(null, host, port);
	}

	public static Authority Authority(UserInfo userInfo, Host host, Port port) {
		return new Authority(userInfo, host, port);
	}

	private final UserInfo userInfo;
	private final Host host;
	private final Port port;

	private Authority(UserInfo userInfo, Host host, Port port) {
		super();
		this.userInfo = userInfo;
		this.host = host;
		this.port = port;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public Host getHost() {
		return host;
	}

	public Port getPort() {
		return port;
	}

	@Override public Authority toImmutable() {
		return this;
	}

	@Override public String toString() {
		String userInfoAndHost = buildUserInfoAndHost();
		return (port == null) ? userInfoAndHost : userInfoAndHost + ":" + port;
	}

	public String toString(Scheme scheme) {
		String userInfoAndHost = buildUserInfoAndHost();
		if (port == null || scheme.isDefaultPort(port)) {
			return userInfoAndHost;
		} else {
			return userInfoAndHost + ":" + port;
		}
	}

	private String buildUserInfoAndHost() {
		return (userInfo == null) ? host.toString() : userInfo + "@" + host;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Authority authority = (Authority) o;

		if (userInfo != null ? !userInfo.equals(authority.userInfo) : authority.userInfo != null) return false;
		if (!host.equals(authority.host)) return false;
		if (port != null ? !port.equals(authority.port) : authority.port != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userInfo != null ? userInfo.hashCode() : 0;
		result = 31 * result + host.hashCode();
		result = 31 * result + (port != null ? port.hashCode() : 0);
		return result;
	}
}
