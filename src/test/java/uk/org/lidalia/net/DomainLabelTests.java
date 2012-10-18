package uk.org.lidalia.net;

import static org.junit.Assert.fail;

import org.junit.Test;

import uk.org.lidalia.net.DomainLabel;

public class DomainLabelTests {

    @Test public void validDomainLabels() {
        valid("abc");
        valid("A0c");
        valid("A-0c");
        valid("0--0");
        valid("");
        valid("a");
    }

    @Test public void invalidDomainLabels() {
        invalid("A0c-");
        invalid("-A0c");
        invalid("-A0c");
    }

    private void valid(String domainLabelStr) {
        DomainLabel.DomainLabel(domainLabelStr);
    }

    private void invalid(String domainLabelStr) {
        try {
            DomainLabel.DomainLabel(domainLabelStr);
            fail(domainLabelStr + " should not be a valid domain label");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

}
