package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Immutable;

import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.Path.Path;

public class HierarchicalPart implements Immutable {

	public static HierarchicalPart HierarchicalPart(String hierarchicalPart) {
		Authority authority;
		Path path;
		if (hierarchicalPart.startsWith("//")) {
			String hierarchicalPartWithoutStart = hierarchicalPart.substring(2);
			String authorityStr = StringUtils.substringBefore(hierarchicalPartWithoutStart, "/");
			authority = Authority(authorityStr);
			path = Path(StringUtils.substringAfter(hierarchicalPartWithoutStart, authorityStr));
		} else {
			authority = null;
			path = Path(hierarchicalPart);
		}
		return new HierarchicalPart(authority, path);
	}

	public static HierarchicalPart HierarchicalPart(Authority authority, Path path) {
		return new HierarchicalPart(authority, path);
	}

	public static HierarchicalPart HierarchicalPart(Path path) {
		return new HierarchicalPart(null, path);
	}

	private final Authority authority;
	private final Path path;

	private HierarchicalPart(Authority authority, Path path) {
		super();
		this.authority = authority;
		this.path = path;
	}

	public Authority getAuthority() {
		return authority;
	}

	public Path getPath() {
		return path;
	}

	@Override public String toString() {
		if (authority != null) {
			return "//" + authority.toString() + path.toString();
		} else {
			return path.toString();
		}
	}

	public String toString(Scheme scheme) {
		if (authority != null) {
			return "//" + authority.toString(scheme) + path.toString();
		} else {
			return path.toString();
		}
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HierarchicalPart that = (HierarchicalPart) o;

		if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;
		if (!path.equals(that.path)) return false;

		return true;
	}

	@Override public int hashCode() {
		int result = authority != null ? authority.hashCode() : 0;
		result = 31 * result + path.hashCode();
		return result;
	}

	@Override public HierarchicalPart toImmutable() {
		return this;
	}
}
