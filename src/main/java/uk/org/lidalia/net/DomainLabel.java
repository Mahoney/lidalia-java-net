package uk.org.lidalia.net;

import org.apache.commons.lang3.Validate;

import uk.org.lidalia.lang.WrappedString;

public class DomainLabel extends WrappedString {
	
	public static DomainLabel DomainLabel(String domainLabelStr) {
		return new DomainLabel(domainLabelStr);
	}

	private DomainLabel(String domainLabelStr) {
		super(domainLabelStr.toLowerCase());
		Validate.matchesPattern(domainLabelStr, "^()|([a-zA-Z0-9])|([a-zA-Z0-9])[a-zA-Z0-9\\-]*[a-zA-Z0-9]$");
	}
}
