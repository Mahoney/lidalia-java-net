package uk.org.lidalia.net;

import org.apache.commons.lang3.Validate;

import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.WrappedValue;

public final class Port extends WrappedValue<Integer> implements Immutable<Port> {

    public static Port Port(String portStr) {
        return Port(Integer.parseInt(portStr));
    }

    public static Port Port(Integer portNumber) {
        return new Port(portNumber);
    }

    private Port(Integer portNumber) {
        super(portNumber);
        Validate.notNull(portNumber, "portNumber is null");
        Validate.isTrue(portNumber >= 0, "portNumber < 0");
        Validate.isTrue(portNumber <= 65535, "portNumber > 65535");
    }

    public Integer getPortNumber() {
        return getWrappedValue();
    }

    @Override
    public Port toImmutable() {
        return this;
    }
}
