package com.thefirstlineofcode.crystal.examples.plugins.data.accessor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thefirstlineofcode.crystal.framework.data.IIdProvider;

@Entity
@Table(name="POSTS")
public class Post implements IIdProvider<Long> {
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
