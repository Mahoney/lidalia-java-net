package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;

import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.Path.Path;

public class HierarchicalPart extends RichObject implements Immutable {

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

	@Identity private final Authority authority;
	@Identity private final Path path;

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

	@Override public HierarchicalPart toImmutable() {
		return this;
	}
}
