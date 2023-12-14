package com.thefirstlineofcode.crystal.examples.plugins.crud;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thefirstlineofcode.crystal.framework.data.IIdProvider;

@Entity
@Table(name="USERS")
public class User implements IIdProvider<Long> {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 32, nullable = false, unique = true)
	private String name;
	@Column(length = 32, nullable = false)
	private String username;
	@Embedded
	private Address address;
	@Column(length = 16)
	private String phone;
	@Column(length = 64)
	private String website;
	@Embedded
	private Company company;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
/*	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}*/
}
