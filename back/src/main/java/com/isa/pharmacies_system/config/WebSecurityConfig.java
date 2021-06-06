package com.isa.pharmacies_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.isa.pharmacies_system.security.TokenUtils;
import com.isa.pharmacies_system.security.auth.RestAuthenticationEntryPoint;
import com.isa.pharmacies_system.security.auth.TokenAuthenticationFilter;
import com.isa.pharmacies_system.service.CustomUserDetailsService;

@Configuration
//Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Service used for reading application users data
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	// Handler for returning 401 when a client with invalid username and password
	// tries to access the resource
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	// Registering the authentication manager which will do the user authentication
	// for us
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Definition of instructions for the authentication manager
	// Defining which service should the authentication manager use to extract user
	// for authentication data
	// Defining which encoder should be used to encode the password from the user's
	// request so the resulting hash can be compared with the hash from the database
	// using the bcrypt algorithm
	// (the password in the database is not in plain text)
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Injection of TokenUtils class implementation so we can use it's methods for
	// JWT in TokenAuthenticationFilteru
	@Autowired
	private TokenUtils tokenUtils;

	// Defining access rules for specific URL
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http

				// Communication between the client and server is statelss because it is a REST
				// application
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				// All unauthenticated request should be uniform processed and return 401 error
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

				// Allow access to paths /auth/**, (/h2-console/** if H2 database is used) and
				// /api/foo to all users
				.authorizeRequests()
				.antMatchers("/auth/login").permitAll()
				.antMatchers("/auth/signup").permitAll()
				.antMatchers("/api/pharmacy/all").permitAll()
				.antMatchers("/api/medicine/all").permitAll()

				.antMatchers("/api/pharmacy/search/*").permitAll()
				.antMatchers("/api/pharmacy/filter").permitAll()
				.antMatchers("/api/pharmacy/sortByCity/*").permitAll()
				.antMatchers("/api/pharmacy/sortByRating/*").permitAll()
				.antMatchers("/api/pharmacy/sortByName/*").permitAll()


				.antMatchers("/auth/confirm_account/*").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/api/foo").permitAll()


				// For any other request the user must be authenticated
				.anyRequest().authenticated().and()

				// For development purposes turn on CORS configuration from WebConfig class
				//.cors().disable()
				.cors().and()

				// Add custom filter TokenAuthenticationFilter for JWT token check instead of
				// using the username and password (which is done with the
				// BasicAuthenticationFilter)
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
						BasicAuthenticationFilter.class)

				// Cross-Site Scripting (XSS) Attack
				.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");

		// Because of the simplicity of the example
		http.csrf().disable();
	}

	// General application security
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter will ignore everything under the listed path
		web.ignoring().antMatchers(HttpMethod.PUT, "/auth/confirm_account/*","/api/pharmacy/sortByCity/*","/api/pharmacy/sortByRating/*","/api/pharmacy/sortByName/*","/api/pharmacy/filter","/api/pharmacy/search/*");
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login","/auth/signup");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js","/api/pharmacy/all","/api/medicine/all");
	}
}
