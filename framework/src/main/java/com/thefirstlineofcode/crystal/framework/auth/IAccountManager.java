package com.thefirstlineofcode.crystal.framework.auth;

public interface IAccountManager {
	void add(String userName, String password);
	void add(IAccount account);
	void remove(String name);
	boolean exists(String name);
	IAccount get(String name);
}
