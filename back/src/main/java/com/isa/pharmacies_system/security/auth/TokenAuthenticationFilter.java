package com.isa.pharmacies_system.security.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.isa.pharmacies_system.security.TokenUtils;

public class TokenAuthenticationFilter extends OncePerRequestFilter{
	
	private TokenUtils tokenUtils;

	private UserDetailsService userDetailsService;

	public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
		this.tokenUtils = tokenHelper;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email;
		String authToken = tokenUtils.getToken(request);

		if (authToken != null) {
			// uzmi username iz tokena
			email = tokenUtils.getEmailFromToken(authToken);
			
			if (email != null) {
				// uzmi user-a na osnovu username-a
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				
				// proveri da li je prosledjeni token validan
				if (tokenUtils.validateToken(authToken, userDetails)) {
					// kreiraj autentifikaciju
					TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
					authentication.setToken(authToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		
		// prosledi request dalje u sledeci filter
		filterChain.doFilter(request, response);
	}

}
