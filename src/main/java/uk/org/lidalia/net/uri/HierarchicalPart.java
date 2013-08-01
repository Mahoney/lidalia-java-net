package uk.org.lidalia.net.uri;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.google.common.base.Optional;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;
import uk.org.lidalia.net.ParseException;

import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.Path.Path;

public class HierarchicalPart extends RichObject implements Immutable<HierarchicalPart> {

    private static final HierarchicalPartParser parser = new HierarchicalPartParser();
    public static HierarchicalPart HierarchicalPart(String hierarchicalPart) throws ParseException {
        return parser.parse(hierarchicalPart);
    }

    public static HierarchicalPart HierarchicalPart(Authority authority, Path path) {
        return new HierarchicalPart(Optional.of(authority), path);
    }

    public static HierarchicalPart HierarchicalPart(Path path) {
        return new HierarchicalPart(Optional.<Authority>absent(), path);
    }

    @Identity private final Optional<Authority> authority;
    @Identity private final Path path;

    HierarchicalPart(Optional<Authority> authority, Path path) {
        super();
        Validate.notNull(authority);
        Validate.notNull(path);
        this.authority = authority;
        this.path = path;
    }

    public Optional<Authority> getAuthority() {
        return authority;
    }

    public Path getPath() {
        return path;
    }

    @Override public String toString() {
        if (authority.isPresent()) {
            return "//" + authority.get().toString() + path.toString();
        } else {
            return path.toString();
        }
    }

    public String toString(Scheme scheme) {
        if (authority.isPresent()) {
            return "//" + authority.get().toString(scheme) + path.toString();
        } else {
            return path.toString();
        }
    }

    @Override public HierarchicalPart toImmutable() {
        return this;
    }

    boolean equals(HierarchicalPart other, Scheme scheme) {
        if (!path.equals(other.path)) return false;
        if (authority.isPresent()) {
            return other.authority.isPresent() && authority.get().equals(other.authority.get(), scheme);
        } else {
            return !other.authority.isPresent();
        }
    }

    int hashCode(Scheme scheme) {
        final int prime = 31;
        int result = 1;
        result = prime * result + (authority.isPresent() ? authority.get().hashCode(scheme) : authority.hashCode());
        result = prime * result + path.hashCode();
        return result;
    }
}
