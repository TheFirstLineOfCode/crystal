package com.thefirstlineofcode.crystal.framework.data;

import org.springframework.data.domain.Pageable;

public class QueryParams {
	public String path;
	public Pageable pageable;
	public Filters filters;
	
	public QueryParams(String path, Pageable pageable, Filters filters) {
		this.path = path;
		this.pageable = pageable;
		this.filters = filters;
		this.sort = sort;
	}
}
