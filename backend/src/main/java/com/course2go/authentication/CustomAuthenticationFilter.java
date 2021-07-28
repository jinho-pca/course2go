package com.course2go.authentication;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.course2go.exception.InputNotFoundException;
import com.course2go.model.user.MyUserDetails;
import com.course2go.model.user.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2 
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
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

