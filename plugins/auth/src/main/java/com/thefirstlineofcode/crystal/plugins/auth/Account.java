package com.thefirstlineofcode.crystal.plugins.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thefirstlineofcode.crystal.framework.auth.IAccount;
import com.thefirstlineofcode.crystal.framework.data.IIdProvider;

@Entity
@Table(name = "accounts")
public class Account implements IIdProvider<Integer>, IAccount {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 32, nullable = false, unique = true)
	private String name;
	@Column(length = 16, nullable = false)
	private String password;
	
	public Account() {}
	
	public Account(String name, String password) {
		this.name = name;
		this.password = password;
	}	
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
}
