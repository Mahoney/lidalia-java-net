package uk.org.lidalia.net.uri;

import org.junit.Test;

import uk.org.lidalia.net.ParseException;
import uk.org.lidalia.net.uri.HierarchicalPart;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.HierarchicalPart.HierarchicalPart;
import static uk.org.lidalia.net.uri.Path.Path;

public class HierarchicalPartTests {

    @Test public void hierarchicalPartWithOnlyAuthorityWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("//authority");
        assertEquals(of(Authority("authority")), hierarchicalPart.getAuthority());
        assertEquals(Path(""), hierarchicalPart.getPath());
        assertEquals("//authority", hierarchicalPart.toString());
    }

    @Test public void hierarchicalPartWithAuthorityAndSinglePathDelimiterWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("//authority/");
        assertEquals(of(Authority("authority")), hierarchicalPart.getAuthority());
        assertEquals(Path("/"), hierarchicalPart.getPath());
        assertEquals("//authority/", hierarchicalPart.toString());
    }

    @Test public void hierarchicalPartWithAuthorityAndSinglePathSegmentWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("//authority/seg1");
        assertEquals(of(Authority("authority")), hierarchicalPart.getAuthority());
        assertEquals(Path("/seg1"), hierarchicalPart.getPath());
        assertEquals("//authority/seg1", hierarchicalPart.toString());
    }

    @Test public void emptyHierarchicalPartWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("");
        assertEquals(absent(), hierarchicalPart.getAuthority());
        assertEquals(Path(""), hierarchicalPart.getPath());
        assertEquals("", hierarchicalPart.toString());
    }

    @Test public void hierarchicalPartWithSinglePathDelimiterWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("/");
        assertEquals(absent(), hierarchicalPart.getAuthority());
        assertEquals(Path("/"), hierarchicalPart.getPath());
        assertEquals("/", hierarchicalPart.toString());
    }

    @Test public void hierarchicalPartWithSinglePathSegmentWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("/seg1");
        assertEquals(absent(), hierarchicalPart.getAuthority());
        assertEquals(Path("/seg1"), hierarchicalPart.getPath());
        assertEquals("/seg1", hierarchicalPart.toString());
    }

    @Test public void hierarchicalPartWithNoLeadingSlashWorks() throws ParseException {
        HierarchicalPart hierarchicalPart = HierarchicalPart("seg1");
        assertEquals(absent(), hierarchicalPart.getAuthority());
        assertEquals(Path("seg1"), hierarchicalPart.getPath());
        assertEquals("seg1", hierarchicalPart.toString());
    }
}
