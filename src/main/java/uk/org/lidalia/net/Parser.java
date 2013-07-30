package uk.org.lidalia.net;

public interface Parser<T, E extends ParseException> {

    T parse(String stringRepresentation) throws E;
}
