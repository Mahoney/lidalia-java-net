package uk.org.lidalia.net;

import static uk.org.lidalia.lang.UnsignedByte.UnsignedByte;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.UnsignedByte;

public class Ipv4Address extends Host {

    private static final Ipv4AddressParser ipv4AddressParser = new Ipv4AddressParser();
    public static Ipv4Address Ipv4Address(String ipv4AddressStr) throws Ipv4AddressParseException {
        return ipv4AddressParser.parse(ipv4AddressStr);
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

    Ipv4Address(UnsignedByte firstByte, UnsignedByte secondByte,
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
