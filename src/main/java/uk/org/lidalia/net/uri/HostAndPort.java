package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

public class HostAndPort implements Immutable {
	
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

	private Host host;
	private Port port;

	HostAndPort(Host host, Port port) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HostAndPort hostAndPort = (HostAndPort) o;

		if (!this.host.equals(hostAndPort.host)) return false;
		if (port != null ? !port.equals(hostAndPort.port) : hostAndPort.port != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = host.hashCode();
		result = 31 * result + (port != null ? port.hashCode() : 0);
		return result;
	}
}
