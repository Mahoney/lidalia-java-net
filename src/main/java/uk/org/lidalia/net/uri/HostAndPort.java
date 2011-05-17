package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

import static uk.org.lidalia.net.uri.Authority.Authority;

public class HostAndPort extends RichObject implements Immutable {
	
	public static HostAndPort HostAndPort(Host host) {
		return new HostAndPort(host, null);
	}

	public static HostAndPort HostAndPort(Host host, Port port) {
		return new HostAndPort(host, port);
	}

	public static HostAndPort HostAndPort(String hostAndPort) {
		String portStr = StringUtils.substringAfterLast(hostAndPort, ":");
		Port port = (portStr.isEmpty()) ? null : Port.Port(portStr);
		String hostStr = StringUtils.substringBeforeLast(hostAndPort, ":");
		Host host = Host.Host(hostStr);
		return new HostAndPort(host, port);
	}

	@Identity	private Host host;
	@Identity private Port port;

	private HostAndPort(Host host, Port port) {
		this.host = host;
		this.port = port;
	}

	public Host getHost() {
		return host;
	}

	public Port getPort() {
		return port;
	}

	@Override public HostAndPort toImmutable() {
		return this;
	}

	@Override public String toString() {
		return (port == null) ? host.toString() : host + ":" + port;
	}

	public String toString(Scheme scheme) {
		if (port == null || scheme.isDefaultPort(port)) {
			return host.toString();
		} else {
			return host + ":" + port;
		}
	}

	public Authority toAuthority() {
		return Authority(this);
	}
}
