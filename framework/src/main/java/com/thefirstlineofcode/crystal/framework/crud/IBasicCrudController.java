package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import org.pf4j.ExtensionPoint;
import org.springframework.http.HttpHeaders;

public interface IBasicCrudController<T> extends ExtensionPoint {
	public List<T> getList(HttpHeaders httpHeaders, Map<String, String> requestParameters);
	IBasicCrudService<T> getService();
}
