package uk.org.lidalia.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IpLiteralParser implements Parser<IpLiteral, IpLiteralParseException> {

    private final String patternString = "\\[(.*)\\]";
    private final Pattern ipLiteralPattern = Pattern.compile(patternString);

    @Override
    public IpLiteral parse(String ipLiteralString) throws IpLiteralParseException {
        final Matcher ipLiteralMatcher = ipLiteralPattern.matcher(ipLiteralString);
        if (ipLiteralMatcher.matches()) {
            return new Ipv6Address(ipLiteralMatcher.group(1));
        } else {
            throw new IpLiteralParseException(ipLiteralString+" is not an IP Literal - does not match "+patternString);
        }
    }
}
