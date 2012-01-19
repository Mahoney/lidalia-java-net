package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

import static uk.org.lidalia.net.uri.Authority.Authority;

public class HostAndPort extends RichObject implements Immutable {
	
	public static HostAndPort HostAndPort(Host host) {
		return new HostAndPort(host, Optional.<Port>absent());
	}

	public static HostAndPort HostAndPort(Host host, Port port) {
		return new HostAndPort(host, Optional.of(port));
	}

	public static HostAndPort HostAndPort(String hostAndPort) {
		String portStr = StringUtils.substringAfterLast(hostAndPort, ":");
		Optional<Port> port = (portStr.isEmpty()) ? Optional.<Port>absent() : Optional.of(Port.Port(portStr));
		String hostStr = StringUtils.substringBeforeLast(hostAndPort, ":");
		Host host = Host.Host(hostStr);
		return new HostAndPort(host, port);
	}

	@Identity private Host host;
	@Identity private Optional<Port> port;

	private HostAndPort(Host host, Optional<Port> port) {
		Validate.notNull(host);
		Validate.notNull(port);
		this.host = host;
		this.port = port;
	}

	public Host getHost() {
		return host;
	}

	public Optional<Port> getPort() {
		return port;
	}

	@Override public HostAndPort toImmutable() {
		return this;
	}

	@Override public String toString() {
		return (port.isPresent()) ? host + ":" + port.get() : host.toString();
	}

	String toString(Scheme scheme) {
		if (!port.isPresent() || scheme.isDefaultPort(port.get())) {
			return host.toString();
		} else {
			return host + ":" + port.get();
		}
	}

	public Authority toAuthority() {
		return Authority(this);
	}

	boolean equals(HostAndPort other, Scheme scheme) {
		if (!host.equals(other.host)) return false;
		Optional<Port> schemeDefaultPort = scheme.getDefaultPort();
		return (port.or(schemeDefaultPort).equals(other.port.or(schemeDefaultPort)));
	}

	int hashCode(Scheme scheme) {
		final int prime = 31;
		int result = 1;
		result = prime * result + host.hashCode();
		result = prime * result + port.or(scheme.getDefaultPort()).hashCode();
		return result;
	}
}
