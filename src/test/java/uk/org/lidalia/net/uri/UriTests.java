package uk.org.lidalia.net.uri;

import org.junit.Test;

import uk.org.lidalia.net.uri.Uri;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static uk.org.lidalia.net.uri.Fragment.Fragment;
import static uk.org.lidalia.net.uri.HierarchicalPart.HierarchicalPart;
import static uk.org.lidalia.net.uri.Query.Query;
import static uk.org.lidalia.net.uri.Scheme.Scheme;
import static uk.org.lidalia.net.uri.Uri.Uri;

public class UriTests {

	private final String scheme = "foo";
	private final String hierarchicalPart = "//example.com:8042/over/there";
	private final String query = "name=ferret";
	private final String fragment = "nose";
	private final String uriStr = scheme + ":" + hierarchicalPart;
	private final String uriWithQuery = scheme + ":" + hierarchicalPart + "?" + query;
	private final String uriWithFragment = scheme + ":" + hierarchicalPart + "#" + fragment;
	private final String uriWithQueryAndFragment = scheme + ":" + hierarchicalPart + "?" + query + "#" + fragment;

	@Test public void singleStringConstructorForUriWorks() {
		Uri uri = Uri(uriStr);
		assertEquals(Scheme(scheme), uri.getScheme());
		assertEquals(HierarchicalPart(hierarchicalPart), uri.getHierarchicalPart());
		assertEquals(absent(), uri.getQuery());
		assertEquals(absent(), uri.getFragment());
		assertEquals(uriStr, uri.toString());
	}

	@Test public void singleStringConstructorForUriWithQueryWorks() {
		Uri uri = Uri(uriWithQuery);
		assertEquals(Scheme(scheme), uri.getScheme());
		assertEquals(HierarchicalPart(hierarchicalPart), uri.getHierarchicalPart());
		assertEquals(of(Query(query)), uri.getQuery());
		assertEquals(absent(), uri.getFragment());
		assertEquals(uriWithQuery, uri.toString());
	}
	
	@Test public void singleStringConstructorForUriWithFragmentWorks() {
		Uri uri = Uri(uriWithFragment);
		assertEquals(Scheme(scheme), uri.getScheme());
		assertEquals(HierarchicalPart(hierarchicalPart), uri.getHierarchicalPart());
		assertEquals(absent(), uri.getQuery());
		assertEquals(of(Fragment(fragment)), uri.getFragment());
		assertEquals(uriWithFragment, uri.toString());
	}

	@Test public void singleStringConstructorForUriWithQueryAndFragmentWorks() {
		Uri uri = Uri(uriWithQueryAndFragment);
		assertEquals(Scheme(scheme), uri.getScheme());
		assertEquals(HierarchicalPart(hierarchicalPart), uri.getHierarchicalPart());
		assertEquals(of(Query(query)), uri.getQuery());
		assertEquals(of(Fragment(fragment)), uri.getFragment());
		assertEquals(uriWithQueryAndFragment, uri.toString());
	}
	
	@Test public void toStringOmitsDefaultPortForScheme() {
		Uri uri = Uri("http://example.com:80/over/there");
		assertEquals("http://example.com/over/there", uri.toString());
	}
	
	@Test public void equalsForEqualUrisWithDefaultPortForOneScheme() {
		Uri uri1 = Uri("http://example.com:80/over/there");
		Uri uri2 = Uri("http://example.com/over/there");
		
		assertEquals(uri1, uri2);
		assertEquals(uri2, uri1);
	}
	
	@Test public void equalsForUnequalUrisWithDefaultPortForOneScheme() {
		Uri uri1 = Uri("http://example.com/over/there");
		Uri uri2 = Uri("http://example.com:443/over/there");
		
		assertFalse(uri1.equals(uri2));
		assertFalse(uri2.equals(uri1));
	}
	
	@Test public void equalsWorksWithPorts() {
		Uri uri1 = Uri("http://example.com:80/over/there");
		Uri uri2 = Uri("http://example.com:80/over/there");
		
		assertEquals(uri1, uri2);
	}
	
	@Test public void equalsWorksWithoutPorts() {
		Uri uri1 = Uri("http://example.com/over/there");
		Uri uri2 = Uri("http://example.com/over/there");
		
		assertEquals(uri1, uri2);
	}
	
	@Test public void hashCodeForEqualUrisWithDefaultPortForOneScheme() {
		Uri uri1 = Uri("http://example.com:80/over/there");
		Uri uri2 = Uri("http://example.com/over/there");
		
		assertEquals(uri1.hashCode(), uri2.hashCode());
	}
	
	@Test public void hashCodeForEqualUris() {
		Uri uri1 = Uri("http://example.com/over/there");
		Uri uri2 = Uri("http://example.com/over/there");
		
		assertEquals(uri1.hashCode(), uri2.hashCode());
	}
	
	@Test public void hashCodeForUnequalUris() {
		Uri uri1 = Uri("http://example.com:443/over/there");
		Uri uri2 = Uri("http://example.com/over/there");
		
		assertFalse(uri1.hashCode() == uri2.hashCode());
	}
}
