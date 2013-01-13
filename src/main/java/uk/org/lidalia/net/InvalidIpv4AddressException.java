package uk.org.lidalia.net;

public class InvalidIpv4AddressException extends Exception {

    public InvalidIpv4AddressException(String ipv4AddressStr) {
        super(ipv4AddressStr+" is not a valid IPv4 Address");
    }
}
