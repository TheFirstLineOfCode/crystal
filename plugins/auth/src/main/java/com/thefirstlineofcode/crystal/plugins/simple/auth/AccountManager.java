package com.thefirstlineofcode.crystal.plugins.simple.auth;

import com.thefirstlineofcode.crystal.framework.auth.IAccount;
import com.thefirstlineofcode.crystal.framework.auth.IAccountManager;

public class AccountManager implements IAccountManager {

	@Override
	public void add(String userName, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(IAccount account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IAccount get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
