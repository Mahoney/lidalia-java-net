package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import uk.org.lidalia.lang.Immutable;

import static uk.org.lidalia.net.uri.Fragment.Fragment;
import static uk.org.lidalia.net.uri.HierarchicalPart.HierarchicalPart;
import static uk.org.lidalia.net.uri.Query.Query;
import static uk.org.lidalia.net.uri.Scheme.Scheme;

public class Uri implements Immutable {

	public static Uri Uri(String uri) {
		Scheme scheme = Scheme(StringUtils.substringBefore(uri, ":"));
		String withoutScheme = StringUtils.substringAfter(uri, ":");

		String fragmentStr = StringUtils.substringAfterLast(withoutScheme, "#");
		String hierarchicalPartAndQuery = StringUtils.substringBeforeLast(withoutScheme, "#");
		String hierarchicalPartStr = StringUtils.substringBefore(hierarchicalPartAndQuery, "?");
		String queryStr = StringUtils.substringAfter(hierarchicalPartAndQuery, "?");

		HierarchicalPart hierarchicalPart = HierarchicalPart(hierarchicalPartStr);
		Query query = (queryStr.isEmpty() ? null : Query(queryStr));
		Fragment fragment = (fragmentStr.isEmpty() ? null : Fragment(fragmentStr));
		return Uri(scheme, hierarchicalPart, query, fragment);
	}

	public static Uri Uri(String schemeStr, String hierarchicalPartStr) {
		return new Uri(Scheme(schemeStr), HierarchicalPart(hierarchicalPartStr), null, null);
	}

	public static Uri Uri(String schemeStr, String hierarchicalPartStr, String queryStr, String fragmentStr) {
		return new Uri(Scheme(schemeStr), HierarchicalPart(hierarchicalPartStr), Query.Query(queryStr), Fragment(fragmentStr));
	}

	public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart) {
		return new Uri(scheme, hierarchicalPart, null, null);
	}

	public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Query query) {
		return new Uri(scheme, hierarchicalPart, query, null);
	}

	public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Fragment fragment) {
		return new Uri(scheme, hierarchicalPart, null, fragment);
	}

	public static Uri Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Query query, Fragment fragment) {
		return new Uri(scheme, hierarchicalPart, query, fragment);
	}

	private final Scheme scheme;
	private final HierarchicalPart hierarchicalPart;
	private final Query query;
	private final Fragment fragment;

	private Uri(Scheme scheme, HierarchicalPart hierarchicalPart, Query query, Fragment fragment) {
		Validate.notNull(scheme, "scheme is null");
		Validate.notNull(hierarchicalPart, "hierarchical part is null");
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

	public Query getQuery() {
		return query;
	}

	public Fragment getFragment() {
		return fragment;
	}

	@Override public Uri toImmutable() {
		return this;
	}

	@Override public String toString() {
		String baseUri =  scheme + ":" + hierarchicalPart.toString(scheme);
		String uriWithQuery = (query == null) ? baseUri : baseUri + "?" + query;
		return (fragment == null) ? uriWithQuery : uriWithQuery + "#" + fragment;
	}

	public String toStringMaintainingPort() {
		String baseUri =  scheme + ":" + hierarchicalPart.toString();
		String uriWithQuery = (query == null) ? baseUri : baseUri + "?" + query;
		return (fragment == null) ? uriWithQuery : uriWithQuery + "#" + fragment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Uri uri = (Uri) o;

		if (!scheme.equals(uri.scheme)) return false;
		if (!hierarchicalPart.equals(uri.hierarchicalPart)) return false;
		if (query != null ? !query.equals(uri.query) : uri.query != null) return false;
		if (fragment != null ? !fragment.equals(uri.fragment) : uri.fragment != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = scheme.hashCode();
		result = 31 * result + hierarchicalPart.hashCode();
		result = 31 * result + (query != null ? query.hashCode() : 0);
		result = 31 * result + (fragment != null ? fragment.hashCode() : 0);
		return result;
	}
}
