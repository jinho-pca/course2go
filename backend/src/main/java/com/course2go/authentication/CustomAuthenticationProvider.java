package com.course2go.authentication;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.course2go.model.user.MyUserDetails;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	
	private final UserDetailsService userDetailsService;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		
		//AuthenticationFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회
		final String userEmail = token.getName();
		final String userPassword = (String) token.getCredentials();
	
		//UserDetailsService를 통해 DB에서 아이디로 사용자 조회
		final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(userEmail);

		if (!passwordEncoder.matches(userPassword+userDetails.getUserSalt(), userDetails.getPassword())) {
			throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, userPassword, userDetails.getAuthorities());
		
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
