package uk.org.lidalia.net;

class Ipv6AddressParser implements Parser<Ipv6Address, Ipv6AddressParseException> {
    @Override
    public Ipv6Address parse(String ipv6AddressStr) throws Ipv6AddressParseException {
        return new Ipv6Address(ipv6AddressStr);
    }
}
