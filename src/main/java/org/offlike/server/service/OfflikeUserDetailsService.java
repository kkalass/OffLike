package org.offlike.server.service;


import java.util.Random;

import org.offlike.server.data.OfflikeSpringUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.ImmutableList;

public class OfflikeUserDetailsService implements UserDetailsService {

	private MongoDbService _dbService;
	
	@Autowired
	public OfflikeUserDetailsService(MongoDbService dbService) {
		_dbService = dbService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		org.offlike.server.data.User user = _dbService.findUserByUsername(username);
		if (user == null) {
			user = _dbService.createUser(username);
		}
		return new OfflikeSpringUserDetails(user, username, new Random().nextDouble() + "", true, true, true, true, ImmutableList.<GrantedAuthority>of(new GrantedAuthorityImpl("USER_ROLE")));
	}
	
}
