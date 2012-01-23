package uk.org.lidalia.net.uri;

import org.junit.Test;

import com.google.common.base.Optional;

import uk.org.lidalia.net.uri.Authority;
import uk.org.lidalia.net.uri.Scheme;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static uk.org.lidalia.net.Host.Host;
import static uk.org.lidalia.net.Port.Port;
import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.UserInfo.UserInfo;

import static org.junit.Assert.assertEquals;

public class AuthorityTests {

	@Test public void authorityWithUserInfoHostAndPortWorks() {
		Authority authority = Authority("me:pwd@host:8080");
		assertEquals(UserInfo("me:pwd"), authority.getUserInfo());
		assertEquals(Host("host"), authority.getHost());
		assertEquals(Port(8080), authority.getPort().get());
		assertEquals("me:pwd@host:8080", authority.toString());
	}

	@Test public void authorityWithSimpleUserInfoHostAndPortWorks() {
		Authority authority = Authority("me@host:8080");
		assertEquals(UserInfo("me"), authority.getUserInfo());
		assertEquals(Host("host"), authority.getHost());
		assertEquals(Port(8080), authority.getPort().get());
		assertEquals("me@host:8080", authority.toString());
	}
	
	@Test public void authorityWithUserInfoAndHostWorks() {
		Authority authority = Authority("me:pwd@host");
		assertEquals(UserInfo("me:pwd"), authority.getUserInfo());
		assertEquals(Host("host"), authority.getHost());
		assertEquals(Optional.absent(), authority.getPort());
		assertEquals("me:pwd@host", authority.toString());
	}
	
	@Test public void authorityWithSimpleUserInfoAndHostWorks() {
		Authority authority = Authority("me@host");
		assertEquals(UserInfo("me"), authority.getUserInfo());
		assertEquals(Host("host"), authority.getHost());
		assertEquals(absent(), authority.getPort());
		assertEquals("me@host", authority.toString());
	}
	
	@Test public void authorityWithHostAndPortWorks() {
		Authority authority = Authority("host:8080");
		assertEquals(UserInfo(), authority.getUserInfo());
		assertEquals(Host("host"), authority.getHost());
		assertEquals(Port(8080), authority.getPort().get());
		assertEquals("host:8080", authority.toString());
	}
	
	@Test public void authorityWithJustHostWorks() {
		Authority authority = Authority("host");
		assertEquals(UserInfo(), authority.getUserInfo());
		assertEquals(Host("host"), authority.getHost());
		assertEquals(Optional.absent(), authority.getPort());
		assertEquals("host", authority.toString());
	}

	@Test public void toStringOmitsDefaultPortForScheme() {
		Authority authority = Authority("host:8080");
		assertEquals("host:8080", authority.toString(Scheme.HTTP));

		authority = Authority("host:80");
		assertEquals("host", authority.toString(Scheme.HTTP));
	}
}
