package com.fullStack.user.model;

public class AuthToken {

    private String token;
    private String username;
    private String email;
    private String id;

    public AuthToken(){

    }

    public AuthToken(String token, String username, String email, String id) {
		this.token = token;
		this.username = username;
		this.email = email;
		this.id = id;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
