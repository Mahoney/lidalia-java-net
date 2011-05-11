package uk.org.lidalia.net;

import org.junit.Test;
import uk.org.lidalia.net.uri.HierarchicalPart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.HierarchicalPart.HierarchicalPart;
import static uk.org.lidalia.net.uri.Path.Path;

public class HierarchicalPartTests {

	@Test public void hierarchicalPartWithOnlyAuthorityWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("//authority");
		assertEquals(Authority("authority"), hierarchicalPart.getAuthority());
		assertEquals(Path(""), hierarchicalPart.getPath());
		assertEquals("//authority", hierarchicalPart.toString());
	}

	@Test public void hierarchicalPartWithAuthorityAndSinglePathDelimiterWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("//authority/");
		assertEquals(Authority("authority"), hierarchicalPart.getAuthority());
		assertEquals(Path("/"), hierarchicalPart.getPath());
		assertEquals("//authority/", hierarchicalPart.toString());
	}

	@Test public void hierarchicalPartWithAuthorityAndSinglePathSegmentWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("//authority/seg1");
		assertEquals(Authority("authority"), hierarchicalPart.getAuthority());
		assertEquals(Path("/seg1"), hierarchicalPart.getPath());
		assertEquals("//authority/seg1", hierarchicalPart.toString());
	}

	@Test public void emptyHierarchicalPartWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("");
		assertNull(hierarchicalPart.getAuthority());
		assertEquals(Path(""), hierarchicalPart.getPath());
		assertEquals("", hierarchicalPart.toString());
	}

	@Test public void hierarchicalPartWithSinglePathDelimiterWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("/");
		assertNull(hierarchicalPart.getAuthority());
		assertEquals(Path("/"), hierarchicalPart.getPath());
		assertEquals("/", hierarchicalPart.toString());
	}

	@Test public void hierarchicalPartWithSinglePathSegmentWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("/seg1");
		assertNull(hierarchicalPart.getAuthority());
		assertEquals(Path("/seg1"), hierarchicalPart.getPath());
		assertEquals("/seg1", hierarchicalPart.toString());
	}

	@Test public void hierarchicalPartWithNoLeadingSlashWorks() {
		HierarchicalPart hierarchicalPart = HierarchicalPart("seg1");
		assertNull(hierarchicalPart.getAuthority());
		assertEquals(Path("seg1"), hierarchicalPart.getPath());
		assertEquals("seg1", hierarchicalPart.toString());
	}
}
