package uk.org.lidalia.net.uri;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

import uk.org.lidalia.net.ParseException;
import uk.org.lidalia.net.Parser;

import static uk.org.lidalia.net.uri.Authority.Authority;
import static uk.org.lidalia.net.uri.Path.Path;

public class HierarchicalPartParser implements Parser<HierarchicalPart, ParseException> {

    @Override
    public HierarchicalPart parse(String hierarchicalPart) throws ParseException {
        Authority authority;
        Path path;
        if (hierarchicalPart.startsWith("//")) {
            String hierarchicalPartWithoutStart = hierarchicalPart.substring(2);
            String authorityStr = StringUtils.substringBefore(hierarchicalPartWithoutStart, "/");
            authority = Authority(authorityStr);
            path = Path(StringUtils.substringAfter(hierarchicalPartWithoutStart, authorityStr));
        } else {
            authority = null;
            path = Path(hierarchicalPart);
        }
        return new HierarchicalPart(Optional.fromNullable(authority), path);
    }
}
