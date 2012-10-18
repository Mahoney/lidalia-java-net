package uk.org.lidalia.net;

import uk.org.lidalia.lang.Identity;

public class RegisteredName extends Host {

    @Identity private final String name;

    public static RegisteredName RegisteredName(String string) {
        // TODO Auto-generated method stub
        return new RegisteredName(string);
    }

    RegisteredName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
