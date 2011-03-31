package uk.org.lidalia.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static uk.org.lidalia.net.Scheme.Scheme;

import org.junit.Test;

public class SchemeTest {

	@Test(expected=IllegalArgumentException.class)
	public void schemeCannotBeNull() {
		Scheme(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void schemeCannotStartWithNumericCharacter() {
		Scheme("0");
	}
	
	@Test
	public void schemeCanBeSingleAlphabetCharacter() {
		Scheme a = Scheme("a");
		assertEquals("a", a.toString());
		Scheme A = Scheme("A");
		assertEquals("A", A.toString());
		Scheme o = Scheme("o");
		assertEquals("o", o.toString());
		Scheme O = Scheme("O");
		assertEquals("O", O.toString());
		Scheme z = Scheme("z");
		assertEquals("z", z.toString());
		Scheme Z = Scheme("Z");
		assertEquals("Z", Z.toString());
	}
	
	@Test
	public void schemeCanContainAllLegalCharacters() {
		String legal = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$_@.&+-";
		Scheme scheme = Scheme(legal);
		assertEquals(legal, scheme.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void schemeCannotContainIllegalCharacters() {
		Scheme("abcd:efg");
	}

	@Test public void schemeIsSameAsStaticallyRegistered() {
		assertSame(Scheme.HTTP, Scheme("http"));
		assertSame(Scheme.HTTPS, Scheme("https"));
		assertSame(Scheme.FTP, Scheme("ftp"));
		assertSame(Scheme.SSH, Scheme("ssh"));
		assertSame(Scheme.MAILTO, Scheme("mailto"));
		assertSame(Scheme.FILE, Scheme("file"));
	}

	@Test public void schemeIsEqualButNotSameAsUnregistered() {
		assertNotSame(Scheme("unregistered"), Scheme("unregistered"));
		assertEquals(Scheme("unregistered"), Scheme("unregistered"));
	}

	@Test public void schemeIsSameAsRegistered() {
		Scheme.register("register");
		assertSame(Scheme("register"), Scheme("register"));
	}
}
