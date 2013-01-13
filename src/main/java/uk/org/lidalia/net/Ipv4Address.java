package uk.org.lidalia.net;

import static uk.org.lidalia.lang.UnsignedByte.UnsignedByte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.UnsignedByte;

public class Ipv4Address extends Host {

    private static final String DEC_OCTET_RGX = "25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]";
    private static final Pattern IPV4_ADDRESS_PATTERN = Pattern.compile(
            "("+DEC_OCTET_RGX+")\\.("+DEC_OCTET_RGX+")\\.("+DEC_OCTET_RGX+")\\.("+DEC_OCTET_RGX+")");

    public static Ipv4Address Ipv4Address(String ipv4AddressStr) throws InvalidIpv4AddressException {
        final Matcher ipv4AddressMatcher = IPV4_ADDRESS_PATTERN.matcher(ipv4AddressStr);
        if (ipv4AddressMatcher.matches()) {
            return new Ipv4Address(
                    UnsignedByte(ipv4AddressMatcher.group(1)),
                    UnsignedByte(ipv4AddressMatcher.group(2)),
                    UnsignedByte(ipv4AddressMatcher.group(3)),
                    UnsignedByte(ipv4AddressMatcher.group(4)));
        } else {
            throw new InvalidIpv4AddressException(ipv4AddressStr);
        }
    }

    public static Ipv4Address Ipv4Address(UnsignedByte firstByte, UnsignedByte secondByte, UnsignedByte thirdByte, UnsignedByte fourthByte) {
        return new Ipv4Address(firstByte, secondByte, thirdByte, fourthByte);
    }

    public static Ipv4Address Ipv4Address(int firstByte, int secondByte, int thirdByte, int fourthByte) {
        return new Ipv4Address(UnsignedByte(firstByte), UnsignedByte(secondByte), UnsignedByte(thirdByte), UnsignedByte(fourthByte));
    }

    @Identity private final UnsignedByte firstByte;
    @Identity private final UnsignedByte secondByte;
    @Identity private final UnsignedByte thirdByte;
    @Identity private final UnsignedByte fourthByte;

    public Ipv4Address(UnsignedByte firstByte, UnsignedByte secondByte,
            UnsignedByte thirdByte, UnsignedByte fourthByte) {
        super();
        this.firstByte = firstByte;
        this.secondByte = secondByte;
        this.thirdByte = thirdByte;
        this.fourthByte = fourthByte;
    }

    @Override
    public String toString() {
        return firstByte+"."+secondByte+"."+thirdByte+"."+fourthByte;
    }
}
