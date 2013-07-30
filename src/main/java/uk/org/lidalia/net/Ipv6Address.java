package uk.org.lidalia.net;

import uk.org.lidalia.lang.Identity;

public class Ipv6Address extends IpLiteral {

    private static Ipv6AddressParser parser = new Ipv6AddressParser();
    public static Ipv6Address Ipv6Address(String ipv6AddressString) throws Ipv6AddressParseException {
        return parser.parse(ipv6AddressString);
    }

    @Identity private final String ipv6;

    Ipv6Address(String ipv6) {
        this.ipv6 = ipv6;
    }

    @Override
    public String toString() {
        return ipv6;
    }
}
