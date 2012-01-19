package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

import static com.google.common.base.Optional.of;
import static uk.org.lidalia.net.uri.UserInfo.UserInfo;
import static uk.org.lidalia.net.uri.HostAndPort.HostAndPort;

public class Authority extends RichObject implements Immutable {

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
		Optional<UserInfo> userInfo = (userInfoStr == null) ? Optional.<UserInfo>absent() : of(UserInfo(userInfoStr));
		HostAndPort hostAndPort = HostAndPort.HostAndPort(hostAndPortStr);
		return new Authority(userInfo, hostAndPort);
	}

	public static Authority Authority(Host host) {
		return Authority(HostAndPort(host));
	}

	public static Authority Authority(UserInfo userInfo, Host host) {
		return Authority(userInfo, HostAndPort(host));
	}

	public static Authority Authority(Host host, Port port) {
		return Authority(Optional.<UserInfo>absent(), host, port);
	}

	public static Authority Authority(UserInfo userInfo, Host host, Port port) {
		return Authority(userInfo, HostAndPort(host, port));
	}

	public static Authority Authority(HostAndPort hostAndPort) {
		return new Authority(Optional.<UserInfo>absent(), hostAndPort);
	}

	public static Authority Authority(UserInfo userInfo, HostAndPort hostAndPort) {
		return new Authority(of(userInfo), hostAndPort);
	}
	
	private static Authority Authority(Optional<UserInfo> userInfo, Host host, Port port) {
		return new Authority(userInfo, HostAndPort(host, port));
	}

	@Identity private final Optional<UserInfo> userInfo;
	@Identity private final HostAndPort hostAndPort;

	Authority(Optional<UserInfo> userInfo, HostAndPort hostAndPort) {
		super();
		this.userInfo = userInfo;
		this.hostAndPort = hostAndPort;
	}

	public Optional<UserInfo> getUserInfo() {
		return userInfo;
	}

	public HostAndPort getHostAndPort() {
		return hostAndPort;
	}

	public Host getHost() {
		return hostAndPort.getHost();
	}

	public Optional<Port> getPort() {
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
		return (userInfo.isPresent()) ? userInfo.get() + "@" + hostAndPortStr : hostAndPortStr;
	}

	boolean equals(Authority other, Scheme scheme) {
		return userInfo.equals(other.userInfo) && hostAndPort.equals(other.hostAndPort, scheme);
	}

	int hashCode(Scheme scheme) {
		final int prime = 31;
		int result = 1;
		result = prime * result + userInfo.hashCode();
		result = prime * result + hostAndPort.hashCode(scheme);
		return result;
	}
}
