package uk.org.lidalia.net.uri;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;

import static com.google.common.base.Optional.of;
import static java.util.Arrays.asList;
import static uk.org.lidalia.net.uri.UriEncodedString.UriEncodedString;

public class UserInfo extends RichObject implements Iterable<UriEncodedString>, Immutable<UserInfo> {

    private static final Joiner JOINER = Joiner.on(":");

    public static UserInfo UserInfo(String userInfo) {
        if (userInfo.length() == 0) return UserInfo();
        List<String> elements = asList(userInfo.split(":"));
        List<UriEncodedString> userInfoElements = Lists.transform(elements, new Function<String, UriEncodedString>() {
            @Override
            public UriEncodedString apply(String input) {
                return UriEncodedString(input);
            }
        });
        return new UserInfo(userInfoElements);
    }

    public static UserInfo UserInfo(UriEncodedString... userInfoElements) {
        return new UserInfo(asList(userInfoElements));
    }

    public static UserInfo UserInfo(List<UriEncodedString> userInfoElements) {
        return new UserInfo(userInfoElements);
    }

    @Identity private final List<UriEncodedString> userInfoElements;

    private UserInfo(List<UriEncodedString> userInfoElements) {
        super();
        this.userInfoElements = Collections.unmodifiableList(userInfoElements);
    }

    public Optional<UriEncodedString> getUserId() {
        return userInfoElements.size() > 0 ?
                of(userInfoElements.get(0))
                : Optional.<UriEncodedString>absent();
    }

    @Override
    public UserInfo toImmutable() {
        return this;
    }

    @Override
    public String toString() {
        return JOINER.join(userInfoElements);
    }

    @Override
    public Iterator<UriEncodedString> iterator() {
        return userInfoElements.iterator();
    }

    public int size() {
        return userInfoElements.size();
    }

    public UriEncodedString get(int index) {
        return userInfoElements.get(index);
    }

    public boolean hasElements() {
        return userInfoElements.size() > 0;
    }
}
