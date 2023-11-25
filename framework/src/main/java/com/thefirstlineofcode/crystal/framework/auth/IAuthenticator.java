package com.thefirstlineofcode.crystal.framework.auth;

public interface IAuthenticator {
	Object getCredentials(Object principal) throws PrincipalNotFoundException;
	boolean exists(Object principal);
}
