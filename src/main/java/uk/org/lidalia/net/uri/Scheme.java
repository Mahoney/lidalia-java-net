package uk.org.lidalia.net.uri;

import static uk.org.lidalia.lang.RichOptional.fromNullable;
import static uk.org.lidalia.net.Port.Port;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.WrappedString;
import uk.org.lidalia.net.Port;

public final class Scheme extends WrappedString {

    private static final String VALID_SCHEME_REGEX = "[a-zA-Z][a-zA-Z0-9+\\-\\.]*";
    private static final Pattern VALID_SCHEME_PATTERN = Pattern.compile(VALID_SCHEME_REGEX);

    private static final ConcurrentMap<String, Scheme> schemes = new ConcurrentHashMap<String, Scheme>();

    public static final Scheme HTTP = register("http", Port(80));
    public static final Scheme HTTPS = register("https", Port(443));
    public static final Scheme FTP = register("ftp", Port(21));
    public static final Scheme SSH = register("ssh", Port(22));
    public static final Scheme MAILTO = register("mailto");
    public static final Scheme FILE = register("file");

    public static Scheme register(String schemeName) {
        return register(schemeName, Optional.<Port>absent());
    }

    public static Scheme register(String schemeName, Port defaultPort) {
        return register(schemeName, Optional.of(defaultPort));
    }

    private static Scheme register(String schemeName, Optional<Port> defaultPort) {
        final Scheme value = new Scheme(schemeName, defaultPort);
        return fromNullable(schemes.putIfAbsent(schemeName.toLowerCase(), value)).or(value);
    }

    private final Optional<Port> defaultPort;

    public static Scheme Scheme(String schemeName) {
        return Scheme(schemeName, Optional.<Port>absent());
    }

    public static Scheme Scheme(String schemeName, Port defaultPort) {
        return Scheme(schemeName, Optional.of(defaultPort));
    }

    private static Scheme Scheme(String schemeName, Optional<Port> defaultPort) {
        Validate.notNull(schemeName);
        schemeName = schemeName.toLowerCase();
        Scheme knownScheme = schemes.get(schemeName);
        if (knownScheme == null) {
            return new Scheme(schemeName, defaultPort);
        } else {
            return knownScheme;
        }
    }

    private Scheme(String schemeName, Optional<Port> defaultPort) {
        super(schemeName);
        Validate.notNull(defaultPort);
        Validate.isTrue(VALID_SCHEME_PATTERN.matcher(schemeName).matches(), "schemeName does not match " + VALID_SCHEME_REGEX);
        this.defaultPort = defaultPort;
    }

    public Optional<Port> getDefaultPort() {
        return defaultPort;
    }

    public boolean isDefaultPort(Port port) {
        return defaultPort.isPresent() && port.equals(defaultPort.get());
    }

    @Override
    public Scheme toImmutable() {
        return this;
    }
}
