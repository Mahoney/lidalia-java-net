package uk.org.lidalia.net;

import uk.org.lidalia.lang.RichObject;

public abstract class Host extends RichObject {

    private static final HostParser hostParser = new HostParser();
    public static Host Host(String hostStr) {
        return hostParser.parse(hostStr);
    }

    Host() {
    }
}
