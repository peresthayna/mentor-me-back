package br.edu.fema.security;

import org.springframework.security.core.AuthenticationException;

public class LoginAuthenticationException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public LoginAuthenticationException(String msg) {
		super(msg);
	}
}


