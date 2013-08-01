package uk.org.lidalia.net.uri;

import org.apache.commons.lang3.Validate;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.Exceptions;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.net.ParseException;

import java.net.URI;
import java.net.URISyntaxException;

import static com.google.common.base.Optional.of;
import static uk.org.lidalia.net.uri.HierarchicalPart.HierarchicalPart;
import static uk.org.lidalia.net.uri.Scheme.Scheme;

public final class Uri implements Immutable<Uri> {

    private static final UriParser uriParser = new UriParser();

    public static Uri Uri(String uri) throws ParseException {
        return uriParser.parse(uri);
    }

    public static Uri Uri(URI javaUri) {
        try {
            return uriParser.parse(javaUri.toString());
        } catch (ParseException e) {
            return Exceptions.throwUnchecked(e, null);
        }
    }

    public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart) {
        return new Uri(scheme, hierarchicalPart, Optional.<Query>absent(), Optional.<Fragment>absent());
    }

    public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Query query) {
        return new Uri(scheme, hierarchicalPart, of(query), Optional.<Fragment>absent());
    }

    public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Fragment fragment) {
        return new Uri(scheme, hierarchicalPart, Optional.<Query>absent(), of(fragment));
    }

    public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Query query, Fragment fragment) {
        return new Uri(scheme, hierarchicalPart, of(query), of(fragment));
    }

    private final Scheme scheme;
    private final HierarchicalPart hierarchicalPart;
    private final Optional<Query> query;
    private final Optional<Fragment> fragment;

    Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Optional<Query> query, Optional<Fragment> fragment) {
        Validate.notNull(scheme, "scheme is null");
        Validate.notNull(hierarchicalPart, "hierarchical part is null");
        Validate.notNull(query, "query is null");
        Validate.notNull(fragment, "fragment is null");
        this.scheme = scheme;
        this.hierarchicalPart = hierarchicalPart;
        this.query = query;
        this.fragment = fragment;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public HierarchicalPart getHierarchicalPart() {
        return hierarchicalPart;
    }

    public Optional<Query> getQuery() {
        return query;
    }

    public Optional<Fragment> getFragment() {
        return fragment;
    }

    public Optional<Authority> getAuthority() {
        return hierarchicalPart.getAuthority();
    }

    public Path getPath() {
        return hierarchicalPart.getPath();
    }

    public URI toURI() {
        try {
            return new URI(scheme.toString(), getAuthority().toString(), getPath().toString(), query.toString(), fragment.toString());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("parse [" + this + "] should always be a valid java.net.URI", e);
        }
    }

    @Override public Uri toImmutable() {
        return this;
    }

    @Override public String toString() {
        return toString(hierarchicalPart.toString(scheme));
    }

    public String toStringMaintainingPort() {
        return toString(hierarchicalPart.toString());
    }

    private String toString(String hierarchicalPartStr) {
        String baseUri =  scheme + ":" + hierarchicalPartStr;
        String uriWithQuery = (query.isPresent()) ? baseUri + "?" + query.get() : baseUri;
        return (fragment.isPresent()) ? uriWithQuery + "#" + fragment.get() : uriWithQuery;
    }

    @Override public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!other.getClass().equals(Uri.class)) {
            return false;
        }
        Uri otherUri = (Uri) other;
        return scheme.equals(otherUri.scheme)
                && query.equals(otherUri.query)
                && fragment.equals(otherUri.fragment)
                && hierarchicalPart.equals(otherUri.hierarchicalPart, scheme);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fragment.hashCode();
        result = prime * result + hierarchicalPart.hashCode(scheme);
        result = prime * result + query.hashCode();
        result = prime * result + scheme.hashCode();
        return result;
    }
}
