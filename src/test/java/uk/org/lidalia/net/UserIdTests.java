package uk.org.lidalia.net;

import org.junit.Test;
import uk.org.lidalia.net.uri.UserId;

import static org.junit.Assert.assertEquals;

public class UserIdTests {

	@Test public void userIdCanBeMadeFromValidChars() {
		String legalUserIdChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~!$&'()*+,;=";
		UserId userId = UserId.UserId(legalUserIdChars);
		assertEquals(legalUserIdChars, userId.toString());
	}
}
