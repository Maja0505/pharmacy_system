package com.isa.pharmacies_system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.isa.pharmacies_system.DTO.PatientNewDTO;
import com.isa.pharmacies_system.DTO.UserTokenStateDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.domain.user.ConfirmationToken;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.TypeOfUser;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.security.TokenUtils;
import com.isa.pharmacies_system.security.auth.JwtAuthenticationRequest;
import com.isa.pharmacies_system.service.AuthorityService;
import com.isa.pharmacies_system.service.CustomUserDetailsService;
import com.isa.pharmacies_system.service.UserService;
import com.isa.pharmacies_system.service.iService.IConfirmationTokenService;
import com.isa.pharmacies_system.service.iService.IPatientService;
/*import com.isa.pharmaciessystem.DTO.RoleDTO;
import com.isa.pharmaciessystem.DTO.UserRegistrationDTO;*/
import com.isa.pharmaciessystem.exception.ResourceConflictException;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	private TokenUtils tokenUtils;

	private AuthenticationManager authenticationManager;

	private CustomUserDetailsService userDetailsService;
	
	private UserService userService;
	
	private PatientConverter patientConverter;
	
	private IPatientService iPatientService;
	
	private PasswordEncoder passwordEncoder;
	
	private AuthorityService authorityService;
	
	private IConfirmationTokenService iConfirmationTokenService;
	
	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager,
			CustomUserDetailsService userDetailsService, UserService userService,TokenUtils tokenUtils, PasswordEncoder passwordEncoder,
			IPatientService iPatientService, AuthorityService authorityService, IConfirmationTokenService iConfirmationTokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.userService = userService;
		this.tokenUtils = tokenUtils;
		this.passwordEncoder = passwordEncoder;
		this.patientConverter = new PatientConverter(passwordEncoder);
		this.iPatientService = iPatientService;
		this.authorityService = authorityService;
		this.iConfirmationTokenService = iConfirmationTokenService;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value="/login", consumes = "application/json")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		try {
		if (authenticationManager == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		} else if (authenticationRequest == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
						authenticationRequest.getPassword()));
		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (tokenUtils == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		// Kreiraj token za tog korisnika
		Users user = (Users) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		
		if (!user.isEnableLogin()) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		
		//cuvanje tokena
		//tokenService.addToken(jwt, user.getId());
		// Vrati token kao odgovor na uspesnu autentifikaciju
		return new ResponseEntity<>(new UserTokenStateDTO(user.getId(),jwt, expiresIn, user.getEmail(), getRoleString(user.getTypeOfUser()),user.isFirstLogin()), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	private String getRoleString(TypeOfUser typeOfUser) {
		switch (typeOfUser) {
		case System_admin:
			return "System_admin";
		case Dermatologist:
			return "Dermatologist";
		case Pharmacy_admin:
			return "Pharmacy_admin";
		case Pharmacist:
			return "Pharmacist";
		case Patient:
			return "Patient";
		case Supplier:
			return "Supplier";
		default:
			return "ERROR";
		}
	}

	@PostMapping(value = "/signup", consumes = "application/json")
	public ResponseEntity<Users> addUser(@RequestBody PatientNewDTO patientNewDTO, UriComponentsBuilder ucBuilder) {
		// TODO Auto-generated method stub
		Users existUser = this.userService.findByEmail(patientNewDTO.getEmail());
		if (existUser != null) {
			throw new ResourceConflictException(Integer.toUnsignedLong(400), "Username already exists");
		}

		Users user = null;
		try {
			Patient patientNew = patientConverter.convertPatientNewDTOToPatient(patientNewDTO);
			patientNew.setAuthorities(authorityService.findByName("ROLE_PATIENT"));
			user = this.userService.save((Users)patientNew);
			this.iPatientService.createPatient(patientNew);
			//HttpHeaders headers = new HttpHeaders();
			//headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}catch (NullPointerException e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}catch (InterruptedException e) {
		    // Restore interrupted state...
			Thread.currentThread().interrupt();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/confirm_account/{token}", consumes = "application/json")
	public ResponseEntity<Boolean> confirmAccount(@PathVariable String token) {
		try {

			ConfirmationToken confirmationToken = iConfirmationTokenService.findByConfirmationToken(token);
			if (confirmationToken != null) {
				setConfirmedAccount(confirmationToken);
				return new ResponseEntity<>(HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	private void setConfirmedAccount(ConfirmationToken confirmationToken) {
		Users users = userService.findByEmail(confirmationToken.getUsers().getEmail());
		users.setEnableLogin(true);
		userService.save(users);
	}

	public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String email = this.tokenUtils.getEmailFromToken(token);
		Users user = (Users) this.userDetailsService.loadUserByUsername(email);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return new ResponseEntity<>(new UserTokenStateDTO(user.getId(),refreshedToken, expiresIn, user.getEmail(), getRoleString(user.getTypeOfUser()),user.isFirstLogin()), HttpStatus.OK);
		} else {
			UserTokenStateDTO userTokenState = new UserTokenStateDTO();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 @GetMapping(value = "/getRole/{token}")
		public ResponseEntity<RoleDTO> getRole(@PathVariable String token) {
			if(token == null) {
				System.out.println("null je token");
			}
			
			
			Users user = userService.findByEmail(this.tokenUtils.getEmailFromToken(token));
			
			System.out.println("TOKEN MEJL: "+ this.tokenUtils.getEmailFromToken(token));
			
			
			String role = user.getTypeOfUser().name();
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setPharmacyAdminId(user.getId());
			roleDTO.setRoleName(role);
			
			if(user.isFirstLogin() == true) {
				roleDTO.setFirstLogin(1);
			}else {
				roleDTO.setFirstLogin(0);
			}
			
			
			return new ResponseEntity<>(roleDTO, HttpStatus.OK);
		}
	 */
	 
	
		@CrossOrigin(origins = "*", allowedHeaders = "*")
		@PostMapping("/logout/{token}")
		 public ResponseEntity<Boolean> logout(@PathVariable String token){
			 try {
				    //tokenService.clearData(token);
		            return new ResponseEntity<>(HttpStatus.OK);
		        }catch(Exception e){
		            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		        }
			
			
			
		}
}
