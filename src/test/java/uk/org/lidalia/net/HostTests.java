package uk.org.lidalia.net;

import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.net.Host.Host;
import static uk.org.lidalia.net.Ipv4Address.Ipv4Address;
import static uk.org.lidalia.net.RegisteredName.RegisteredName;

import org.junit.Test;

public class HostTests {
	
	@Test public void ipv4Address() {
		assertEquals(Ipv4Address(1, 2, 3, 4), Host("1.2.3.4"));
	}
	
	@Test public void domainName() {
		assertEquals(RegisteredName("example.com"), Host("example.com"));
	}
}
