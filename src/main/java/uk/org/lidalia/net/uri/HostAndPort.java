package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.net.Host;
import uk.org.lidalia.net.Port;

public class HostAndPort extends Authority {
	
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

	HostAndPort(Host host, Port port) {
		super(null, host, port);
	}
}
