package com.isa.pharmacies_system.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	protected final Log LOGGER = LogFactory.getLog(getClass());

	private IUserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	private AuthenticationManager authenticationManager;
	
	@Autowired
	public CustomUserDetailsService(IUserRepository userRepository,
			AuthenticationManager authenticationManager) {
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
	}

	private Users findByEmail(String email) {
		for (Users user : userRepository.findAll()) {
			if (user.getEmail().equals(email)) return user;
		}
		return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		String email = username;
		Users user = findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return user;
		}
	}
	
	public void changePassword(String oldPassword, String newPassword) {

		// Ocitavamo trenutno ulogovanog korisnika
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String email = currentUser.getName();

		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + email + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		LOGGER.debug("Changing password for user '" + email + "'");

		Users user = (Users) loadUserByUsername(email);

		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

	}

}
