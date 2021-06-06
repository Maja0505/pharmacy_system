package com.isa.pharmacies_system.DTO;

public class UserTokenStateDTO {

		private Long userId;
	    private String accessToken;
	    private String email;
	    private String role;
	    private Long expiresIn;
	    private Boolean isFirstLogin;

	    public UserTokenStateDTO() {
	    	this.userId = null;
	        this.accessToken = null;
	        this.expiresIn = null;
	        this.email = null;
	        this.role = null;	  
	        this.isFirstLogin = false;
	    }

	    public UserTokenStateDTO(Long userId,String accessToken, long expiresIn,String email, String role, Boolean isFirstLogin) {
	        this.userId = userId;
	    	this.accessToken = accessToken;
	        this.expiresIn = expiresIn;
	        this.email = email;
	        this.role = role;
	        this.isFirstLogin = isFirstLogin;
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

		public Boolean getIsFirstLogin() {
			return isFirstLogin;
		}

		public void setIsFirstLogin(Boolean isFirstLogin) {
			this.isFirstLogin = isFirstLogin;
		}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		isFirstLogin = firstLogin;
	}
}
