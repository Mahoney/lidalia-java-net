package uk.org.lidalia.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static uk.org.lidalia.lang.UnsignedByte.UnsignedByte;

class Ipv4AddressParser implements Parser<Ipv4Address, Ipv4AddressParseException> {
    private static final String DEC_OCTET_RGX = "25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]";
    private static final Pattern IPV4_ADDRESS_PATTERN = Pattern.compile(
            "("+DEC_OCTET_RGX+")\\.("+DEC_OCTET_RGX+")\\.("+DEC_OCTET_RGX+")\\.("+DEC_OCTET_RGX+")");

    @Override
    public Ipv4Address parse(String ipv4AddressStr) throws Ipv4AddressParseException {
        final Matcher ipv4AddressMatcher = IPV4_ADDRESS_PATTERN.matcher(ipv4AddressStr);
        if (ipv4AddressMatcher.matches()) {
            return new Ipv4Address(
                    UnsignedByte(ipv4AddressMatcher.group(1)),
                    UnsignedByte(ipv4AddressMatcher.group(2)),
                    UnsignedByte(ipv4AddressMatcher.group(3)),
                    UnsignedByte(ipv4AddressMatcher.group(4)));
        } else {
            throw new Ipv4AddressParseException(ipv4AddressStr);
        }
    }
}
