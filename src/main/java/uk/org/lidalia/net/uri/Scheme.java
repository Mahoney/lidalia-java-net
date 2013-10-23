package uk.org.lidalia.net.uri;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.WrappedString;
import uk.org.lidalia.net.Port;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Optional.of;
import static uk.org.lidalia.net.Port.Port;

public abstract class Scheme extends WrappedString<Scheme> {

    private static final Pattern VALID_SCHEME_REGEX = Pattern.compile("[a-zA-Z][a-zA-Z0-9+\\-\\.]*");

    private static final ConcurrentMap<String, Scheme> schemes = new ConcurrentHashMap<>();

    public static final Scheme HTTP = Scheme("http", Port(80));
    public static final Scheme HTTPS = Scheme("https", Port(443));
    public static final Scheme FTP = Scheme("ftp", Port(21));
    public static final Scheme SSH = Scheme("ssh", Port(22));
    public static final Scheme MAILTO = new SimpleScheme("mailto");
    public static final Scheme FILE = new SimpleScheme("file");

    public static Scheme Scheme(String name, Port defaultPort) {
        final Scheme value = new SchemeWithDefaultPort(name, defaultPort);
        return fromNullable(schemes.putIfAbsent(name.toLowerCase(), value)).or(value);
    }

    public static Scheme Scheme(String name) {
        return fromNullable(schemes.get(name.toLowerCase())).or(new SimpleScheme(name));
    }

    private final String name;

    private Scheme(String name) {
        super(name);
        Validate.isTrue(VALID_SCHEME_REGEX.matcher(name).matches(), "schemeName does not match " + VALID_SCHEME_REGEX);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Optional<Port> getDefaultPort();

    public static class SimpleScheme extends Scheme {
        private SimpleScheme(String schemeName) {
            super(schemeName);
        }

        public Optional<Port> getDefaultPort() {
            return Optional.absent();
        }
    }

    public static class SchemeWithDefaultPort extends Scheme {
        private final Optional<Port> defaultPort;

        private SchemeWithDefaultPort(String schemeName, Port defaultPort) {
            super(schemeName);
            this.defaultPort = of(defaultPort);
        }

        public Optional<Port> getDefaultPort() {
            return defaultPort;
        }
    }
}
