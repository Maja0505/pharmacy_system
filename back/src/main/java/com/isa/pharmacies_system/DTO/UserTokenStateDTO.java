package com.isa.pharmacies_system.DTO;

public class UserTokenStateDTO {
		
	    private String accessToken;
	    private String email;
	    private String role;
	    private Long expiresIn;

	    public UserTokenStateDTO() {
	        this.accessToken = null;
	        this.expiresIn = null;
	        this.email = null;
	        this.role = null;	        
	    }

	    public UserTokenStateDTO(String accessToken, long expiresIn,String email, String role) {
	        this.accessToken = accessToken;
	        this.expiresIn = expiresIn;
	        this.email = email;
	        this.role = role;
	    }

	    public String getAccessToken() {
	        return accessToken;
	    }

	    public void setAccessToken(String accessToken) {
	        this.accessToken = accessToken;
	    }

	    public Long getExpiresIn() {
	        return expiresIn;
	    }

	    public void setExpiresIn(Long expiresIn) {
	        this.expiresIn = expiresIn;
	    }

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	
}
