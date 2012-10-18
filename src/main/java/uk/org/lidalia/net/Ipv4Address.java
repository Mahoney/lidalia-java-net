package uk.org.lidalia.net;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.Validate.isTrue;
import static uk.org.lidalia.lang.UnsignedByte.from;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.UnsignedByte;

public class Ipv4Address extends Host {

	public static Ipv4Address Ipv4Address(String ipv4AddressStr) {
		List<String> splitAddress = asList(ipv4AddressStr.split("\\."));
		int size = splitAddress.size();
		isTrue(size == 4, "An IP v4 address must contain 4 bytes, had " + size);
		List<UnsignedByte> addressBytes = Lists.transform(splitAddress, new Function<String, UnsignedByte>() {
			@Override
			public UnsignedByte apply(String input) {
				return from(Integer.parseInt(input));
			}
		});
		return new Ipv4Address(addressBytes.get(0), addressBytes.get(1), addressBytes.get(2), addressBytes.get(3));
	}

	public static Ipv4Address Ipv4Address(UnsignedByte firstByte, UnsignedByte secondByte, UnsignedByte thirdByte, UnsignedByte fourthByte) {
		return new Ipv4Address(firstByte, secondByte, thirdByte, fourthByte);
	}

	public static Ipv4Address Ipv4Address(int firstByte, int secondByte, int thirdByte, int fourthByte) {
		return new Ipv4Address(from(firstByte), from(secondByte), from(thirdByte), from(fourthByte));
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
		return firstByte + "." + secondByte + "." + thirdByte + "." + fourthByte;
	}

    public static boolean isIpv4Address(String hostStr) {
        String decOctet = "25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]";
        return hostStr.matches("((" + decOctet + ")\\.){3}(" + decOctet + ")");
    }
}
