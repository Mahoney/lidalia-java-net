package uk.org.lidalia.net;

import org.junit.Test;

import uk.org.lidalia.lang.Task;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static uk.org.lidalia.net.Ipv4Address.Ipv4Address;
import static uk.org.lidalia.test.ShouldThrow.shouldThrow;

public class Ipv4AddressTests {

    private static final String NEWLINE = System.getProperty("line.separator");
    Iterable<String> validDecOctets = asList("0", "1", "9", "10", "99", "100", "199", "200", "255");
    Iterable<String> invalidDecOctets = asList("00", "09", "010", "099", "256", "265", "299", "300", "355");

    @Test
    public void isIpv4AddressValidInput() throws Exception {
        for (String decOctet1: validDecOctets) {
            for (String decOctet2: validDecOctets) {
                for (String decOctet3: validDecOctets) {
                    for (String decOctet4: validDecOctets) {
                        final Ipv4Address expected = Ipv4Address(parseInt(decOctet1), parseInt(decOctet2), parseInt(decOctet3), parseInt(decOctet4));
                        final String ipv4AddressStr = decOctet1 + "." + decOctet2 + "." + decOctet3 + "." + decOctet4;
                        assertThat(Ipv4Address(ipv4AddressStr), is(expected));
                    }
                }
            }
        }
    }

    @Test
    public void isIpv4AddressInvalidOctets() throws Exception {
        for (String invalidDecOctet: invalidDecOctets) {
            assertInvalid(invalidDecOctet + ".0.0.0");
            assertInvalid("0." + invalidDecOctet + ".0.0");
            assertInvalid("0.0." + invalidDecOctet + ".0");
            assertInvalid("0.0.0." + invalidDecOctet);
        }
    }

    @Test
    public void isIpv4Address3Octets() {
        assertInvalid("0.0.0");
    }

    @Test
    public void isIpv4Address5Octets() {
        assertInvalid("0.0.0.0.0");
    }

    @Test
    public void isIpv4Address4Periods() {
        assertInvalid("0.0.0.0.");
    }

    @Test
    public void isIpv4AddressLineBefore() {
        assertInvalid("blah"+NEWLINE+"0.0.0.0");
    }

    @Test
    public void isIpv4AddressLineAfter() {
        assertInvalid("0.0.0.0"+NEWLINE+"blah");
    }

    @Test
    public void isIpv4AddressTextBefore() {
        assertInvalid("blah0.0.0.0");
    }

    @Test
    public void isIpv4AddressTextAfter() {
        assertInvalid("0.0.0.0blah");
    }

    private void assertInvalid(final String invalidIp) {
        Ipv4AddressParseException e = shouldThrow(Ipv4AddressParseException.class, new Task() {
            public void perform() throws Ipv4AddressParseException {
                Ipv4Address(invalidIp);
            }
        });
        assertThat(e.getMessage(), is(invalidIp+" is not a valid IPv4 Address"));
    }
}
