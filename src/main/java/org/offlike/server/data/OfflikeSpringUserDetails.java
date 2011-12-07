package org.offlike.server.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class OfflikeSpringUserDetails extends org.springframework.security.core.userdetails.User {
	private final User _user;

	public OfflikeSpringUserDetails(
			User user,
			String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		_user = user;
	}

	public String getUserId() {
		return _user.getId();
	}
	
}
