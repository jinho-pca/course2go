package com.course2go.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.course2go.exception.InputNotFoundException;
import com.course2go.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;



public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) { 
		super.setAuthenticationManager(authenticationManager); 
	} 
	
	@Override 
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response){ 
		final UsernamePasswordAuthenticationToken authRequest;
		try {
			final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			authRequest = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword());
		} catch (IOException exception){
            throw new InputNotFoundException();
        }
        setDetails(request, authRequest);
        
        return this.getAuthenticationManager().authenticate(authRequest);
	} 
	

	
}
	

	