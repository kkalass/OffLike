package org.offlike.server;

import javax.annotation.CheckForNull;

import org.offlike.server.data.OfflikeSpringUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * TODO: extract interface, convert to Spring Bean, use a test implementation in tests.
 * @author klas
 *
 */
public final class UserContextHolder {

	private UserContextHolder() {
		// utility class
	}
	
	@CheckForNull
	public static String getCurrentUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context == null ? null : context.getAuthentication();
		OfflikeSpringUserDetails userDetails = auth == null ? null : (OfflikeSpringUserDetails) auth.getPrincipal();
		return userDetails == null ? null : userDetails.getUserId();
	}
}
