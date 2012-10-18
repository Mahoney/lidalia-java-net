package uk.org.lidalia.net;

import static uk.org.lidalia.net.Ipv4Address.Ipv4Address;
import uk.org.lidalia.lang.RichObject;

public class Host extends RichObject {

    public static Host Host(String hostStr) {
        if (Ipv4Address.isIpv4Address(hostStr)) {
            return Ipv4Address.Ipv4Address(hostStr);
        } else {
            return RegisteredName.RegisteredName(hostStr);
        }
    }

    Host() {
    }
}
