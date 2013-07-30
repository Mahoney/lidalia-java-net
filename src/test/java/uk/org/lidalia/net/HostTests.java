package uk.org.lidalia.net;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static uk.org.lidalia.net.Host.Host;
import static uk.org.lidalia.net.Ipv4Address.Ipv4Address;
import static uk.org.lidalia.net.Ipv6Address.Ipv6Address;
import static uk.org.lidalia.net.RegisteredName.RegisteredName;

import org.junit.Test;

public class HostTests {

    @Test public void ipv4Address() {
        final Host expected = Ipv4Address(1, 2, 3, 4);
        assertThat(Host("1.2.3.4"), is(expected));
    }

    @Test public void ipv6Address() throws Ipv6AddressParseException {
        final Host expected = Ipv6Address("3ffe:1900:4545:3:200:f8ff:fe21:67cf");
        assertThat(Host("[3ffe:1900:4545:3:200:f8ff:fe21:67cf]"), is(expected));
    }

    @Test public void domainName() {
        final Host expected = RegisteredName("example.com");
        assertThat(Host("example.com"), is(expected));
    }
}
