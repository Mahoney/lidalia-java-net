package uk.org.lidalia.net.uri;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

import uk.org.lidalia.net.ParseException;
import uk.org.lidalia.net.Parser;

public class UriParser implements Parser<Uri, ParseException> {
    public Uri parse(String uri) throws ParseException {
        Scheme scheme = Scheme.Scheme(StringUtils.substringBefore(uri, ":"));
        String withoutScheme = StringUtils.substringAfter(uri, ":");

        String fragmentStr = StringUtils.substringAfterLast(withoutScheme, "#");
        String hierarchicalPartAndQuery = StringUtils.substringBeforeLast(withoutScheme, "#");
        String hierarchicalPartStr = StringUtils.substringBefore(hierarchicalPartAndQuery, "?");
        String queryStr = StringUtils.substringAfter(hierarchicalPartAndQuery, "?");

        HierarchicalPart hierarchicalPart = HierarchicalPart.HierarchicalPart(hierarchicalPartStr);
        Optional<Query> query = (queryStr.isEmpty() ? Optional.<Query>absent() : Optional.of(Query.Query(queryStr)));
        Optional<Fragment> fragment = (fragmentStr.isEmpty() ? Optional.<Fragment>absent() : Optional.of(Fragment.Fragment(fragmentStr)));
        return new Uri(scheme, hierarchicalPart, query, fragment);
    }
}
