package uk.org.lidalia.net.uri;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import uk.org.lidalia.lang.Collections3;
import uk.org.lidalia.lang.Identity;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.RichObject;

import static com.google.common.base.Optional.of;
import static java.util.Arrays.asList;
import static uk.org.lidalia.net.uri.UriText.UriText;

public class UserInfo extends RichObject implements Iterable<UriText>, Immutable {

	public static UserInfo UserInfo(String userInfo) {
		if (userInfo.length() == 0) return UserInfo();
		List<String> elements = asList(userInfo.split(":"));
		List<UriText> userInfoElements = Lists.transform(elements, new Function<String, UriText>() {
			@Override
			public UriText apply(String input) {
				return UriText(input);
			}
		});
		return new UserInfo(userInfoElements);
	}
	
	public static UserInfo UserInfo(UriText... userInfoElements) {
		return new UserInfo(asList(userInfoElements));
	}

	public static UserInfo UserInfo(List<UriText> userInfoElements) {
		return new UserInfo(userInfoElements);
	}

	@Identity private final List<UriText> userInfoElements;

	private UserInfo(List<UriText> userInfoElements) {
		super();
		this.userInfoElements = Collections.unmodifiableList(userInfoElements);
	}

	public Optional<UriText> getUserId() {
		return userInfoElements.size() > 0 ?
				of(userInfoElements.get(0))
				: Optional.<UriText>absent();
	}

	@Override
	public UserInfo toImmutable() {
		return this;
	}

	@Override
	public String toString() {
		return Collections3.toString(userInfoElements, "", ":", "");
	}
	
	@Override
	public Iterator<UriText> iterator() {
		return userInfoElements.iterator();
	}
	
	public int size() {
		return userInfoElements.size();
	}
	
	public UriText get(int index) {
		return userInfoElements.get(index);
	}
	
	public boolean hasElements() {
		return userInfoElements.size() > 0;
	}
}
