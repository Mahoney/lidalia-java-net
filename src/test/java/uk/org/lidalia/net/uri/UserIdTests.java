package uk.org.lidalia.net.uri;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.net.uri.UriText.UriText;

public class UserIdTests {

    @Test public void userIdCanBeMadeFromValidChars() {
        String legalUserIdChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~!$&'()*+,;=";
        UriText userId = UriText(legalUserIdChars);
        assertEquals(legalUserIdChars, userId.toString());
    }
}
