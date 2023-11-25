package com.thefirstlineofcode.crystal.plugins.simple.auth;

import com.thefirstlineofcode.crystal.framework.auth.IAuthenticator;
import com.thefirstlineofcode.crystal.framework.auth.PrincipalNotFoundException;

public class Authenticator implements IAuthenticator {

	@Override
	public Object getCredentials(Object principal) throws PrincipalNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Object principal) {
		// TODO Auto-generated method stub
		return false;
	}

}
