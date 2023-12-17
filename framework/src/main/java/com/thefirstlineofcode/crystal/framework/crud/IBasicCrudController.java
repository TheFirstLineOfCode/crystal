package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

public interface IBasicCrudController<T> {
	public List<T> getList(HttpHeaders httpHeaders, Map<String, String> requestParameters);
	IBasicCrudService<T> getService();
}
