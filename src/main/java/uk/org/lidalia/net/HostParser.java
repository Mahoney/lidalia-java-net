package uk.org.lidalia.net;

class HostParser implements Parser<Host, ParseException> {

    @Override
    public Host parse(String hostStr) {
        try {
            return IpLiteral.IpLiteral(hostStr);
        } catch (IpLiteralParseException invalidIpLiteral) {
            try {
                return Ipv4Address.Ipv4Address(hostStr);
            } catch (Ipv4AddressParseException invalidIpv4) {
                return RegisteredName.RegisteredName(hostStr);
            }
        }
    }
}
