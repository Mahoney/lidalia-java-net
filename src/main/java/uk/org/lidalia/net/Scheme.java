package uk.org.lidalia.net;

import static uk.org.lidalia.net.Port.Port;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.lang.Maps;
import uk.org.lidalia.lang.Utils;
import uk.org.lidalia.lang.WrappedString;

public final class Scheme extends WrappedString {
	
	private static final String VALID_SCHEME_REGEX = "[a-zA-Z][a-zA-Z0-9$_@.&+\\-]*";
	private static final Pattern VALID_SCHEME_PATTERN = Pattern.compile(VALID_SCHEME_REGEX);

	private static final ConcurrentMap<String, Scheme> schemes = new ConcurrentHashMap<String, Scheme>();

	public static final Scheme HTTP = register("http", Port(80));
	public static final Scheme HTTPS = register("https", Port(443));
	public static final Scheme FTP = register("ftp", Port(21));
	public static final Scheme SSH = register("ssh", Port(22));
	public static final Scheme MAILTO = register("mailto", null);
	public static final Scheme FILE = register("file", null);

	public static Scheme register(String schemeName) {
		return register(schemeName, null);
	}
	
	public static Scheme register(String schemeName, Port defaultPort) {
		return Maps.putIfAbsentReturningValue(schemes, schemeName, new Scheme(schemeName, defaultPort));
	}

	private final Port defaultPort;

	public static Scheme Scheme(String schemeName) {
		return Scheme(schemeName, null);
	}

	public static Scheme Scheme(String schemeName, Port defaultPort) {
		Validate.notNull(schemeName, "scheme name");
		Scheme knownScheme = schemes.get(schemeName);
		if (knownScheme == null) {
			return new Scheme(schemeName, defaultPort);
		} else {
			return knownScheme;
		}
	}

	private Scheme(String schemeName, Port defaultPort) {
		super(schemeName);
		Validate.isTrue(VALID_SCHEME_PATTERN.matcher(schemeName).matches(), "schemeName does not match " + VALID_SCHEME_REGEX);
		this.defaultPort = defaultPort;
	}

	public Port getDefaultPort() {
		return defaultPort;
	}

	@Override
	public Scheme toImmutable() {
		return this;
	}
}
