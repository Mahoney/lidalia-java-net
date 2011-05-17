package uk.org.lidalia.net.uri;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;

import java.net.URI;
import java.net.URISyntaxException;

import static uk.org.lidalia.net.uri.Fragment.Fragment;
import static uk.org.lidalia.net.uri.HierarchicalPart.HierarchicalPart;
import static uk.org.lidalia.net.uri.Query.Query;
import static uk.org.lidalia.net.uri.Scheme.Scheme;

public class Uri extends RichObject implements Immutable {

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
	public static Uri Uri(URI javaUri) {
		return Uri(javaUri.toString());
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

	public Authority getAuthority() {
		return hierarchicalPart.getAuthority();
	}

	public Path getPath() {
		return hierarchicalPart.getPath();
	}

	public URI toURI() {
		try {
			return new URI(scheme.toString(), getAuthority().toString(), getPath().toString(), query.toString(), fragment.toString());
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Uri [" + this + "] should always be a valid java.net.URI", e);
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
		String uriWithQuery = (query == null) ? baseUri : baseUri + "?" + query;
		return (fragment == null) ? uriWithQuery : uriWithQuery + "#" + fragment;
	}
}
