package com.isa.pharmacies_system.domain.user;

import static javax.persistence.InheritanceType.JOINED;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.user.Authority;

@Entity
@Table(name = "users")
@Inheritance(strategy = JOINED)
public class Users implements UserDetails {

	@Id
	@SequenceGenerator(name = "mySeqGenUsers", sequenceName = "mySeqUsers", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenUsers")
	private long id;

	@Version
	private Long version;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@Column(name = "firstName", unique = false, nullable = false)
	private String firstName;

	@Column(name = "lastName", unique = false, nullable = false)
	private String lastName;

	private Address userAddress;

	@Column(name = "phoneNumber", unique = false, nullable = false)
	private String phoneNumber;

	@Enumerated(EnumType.ORDINAL)
	private TypeOfUser typeOfUser;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Authority> authorities;

	@Column(name = "enabled", unique = false, nullable = false)
	private boolean enabled;
	
	@Column(name = "enableLogin", unique = false, nullable = false)
	private boolean enableLogin;
	
	@Column(name = "isFirstLogin", unique = false, nullable = false)
	private boolean isFirstLogin;

	@Column(name = "last_password_reset_date", unique = false, nullable = false)
	private Timestamp lastPasswordResetDate;

	public Users() {

	}

	public Users(long id, String email, String password, String firstName, String lastName, Address userAddress,
			String phoneNumber, TypeOfUser typeOfUser) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userAddress = userAddress;
		this.phoneNumber = phoneNumber;
		this.typeOfUser = typeOfUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public TypeOfUser getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(TypeOfUser typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	public Date getLastPasswordResetDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public boolean isEnableLogin() {
		return enableLogin;
	}

	public void setEnableLogin(boolean enableLogin) {
		this.enableLogin = enableLogin;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
