package org.offlike.server;

import javax.annotation.CheckForNull;

import org.offlike.server.data.OfflikeSpringUserDetails;
import org.offlike.server.service.OfflikeUserDetailsService;
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
		
		if (auth != null && auth.getAuthorities().contains(OfflikeUserDetailsService.USER_AUTHORITY)) {
			Object principal = auth.getPrincipal();
			OfflikeSpringUserDetails userDetails = auth == null ? null : (OfflikeSpringUserDetails) principal;
			return userDetails == null ? null : userDetails.getUserId();
		}
		return null;
	}
	
}
