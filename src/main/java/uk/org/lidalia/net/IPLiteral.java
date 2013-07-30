package uk.org.lidalia.net;

public abstract class IpLiteral extends Host {
    private static IpLiteralParser ipLiteralParser = new IpLiteralParser();
    public static IpLiteral IpLiteral(String ipLiteralString) throws IpLiteralParseException {
        return ipLiteralParser.parse(ipLiteralString);
    }
    IpLiteral() {}
}
