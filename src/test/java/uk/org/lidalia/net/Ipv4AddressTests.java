package uk.org.lidalia.net;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Ipv4AddressTests {

    Iterable<String> validDecOctets = asList("0", "1", "9", "10", "99", "100", "199", "200", "255");
    Iterable<String> invalidDecOctets = asList("00", "09", "010", "099", "256", "265", "299", "300", "355");

    @Test
    public void isIpv4AddressValidInput() throws Exception {
        for (String decOctet1: validDecOctets) {
            for (String decOctet2: validDecOctets) {
                for (String decOctet3: validDecOctets) {
                    for (String decOctet4: validDecOctets) {
                        String validIp = decOctet1 + "." + decOctet2 + "." + decOctet3 + "." + decOctet4;
                        assertTrue(validIp + " is a valid ip", Ipv4Address.isIpv4Address(validIp));
                    }
                }
            }
        }
    }

    @Test
    public void isIpv4AddressInvalidOctets() throws Exception {
        for (String invalidDecOctet: invalidDecOctets) {
            String invalidIp = invalidDecOctet + ".0.0.0";
            assertFalse(invalidIp + " is invalid", Ipv4Address.isIpv4Address(invalidIp));
            invalidIp = "0." + invalidDecOctet + ".0.0";
            assertFalse(invalidIp + " is invalid", Ipv4Address.isIpv4Address(invalidIp));
            invalidIp = "0.0." + invalidDecOctet + ".0";
            assertFalse(invalidIp + " is invalid", Ipv4Address.isIpv4Address(invalidIp));
            invalidIp = "0.0.0." + invalidDecOctet;
            assertFalse(invalidIp + " is invalid", Ipv4Address.isIpv4Address(invalidIp));
        }
    }

    @Test
    public void isIpv4Address3Octets() {
        String invalidIp = "0.0.0";
        assertFalse(invalidIp + " is invalid", Ipv4Address.isIpv4Address(invalidIp));
    }

    @Test
    public void isIpv4Address5Octets() {
        String invalidIp = "0.0.0.0.0";
        assertFalse(invalidIp + " is invalid", Ipv4Address.isIpv4Address(invalidIp));
    }
}
