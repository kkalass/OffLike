package org.offlike.server.service;


import java.util.Random;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.ImmutableList;

public class OfflikeUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		return new User(arg0, new Random().nextDouble() + "", true, true, true, true, ImmutableList.<GrantedAuthority>of(new GrantedAuthorityImpl("USER_ROLE")));
	}
}
