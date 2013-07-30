package uk.org.lidalia.net;

public class Ipv4AddressParseException extends ParseException {

    public Ipv4AddressParseException(String ipv4AddressStr) {
        super(ipv4AddressStr+" is not a valid IPv4 Address");
    }
}
