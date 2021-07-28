package com.course2go.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.course2go.model.user.MyUserDetails;
import com.course2go.model.user.User;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	
	public void onAuthenicationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) {
		
		final User user = ((MyUserDetails) authentication.getPrincipal()).getUser();
		final String token = TokenUtils.generateJwtToken(user);
		response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
			
	}
}
