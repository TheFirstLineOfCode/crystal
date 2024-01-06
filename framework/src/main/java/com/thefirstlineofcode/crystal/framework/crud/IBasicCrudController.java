package com.thefirstlineofcode.crystal.framework.crud;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

public interface IBasicCrudController<T, ID> {
	public Object getResources(HttpServletRequest request, HttpHeaders httpHeaders,
			Map<String, String> requestParameters, HttpServletResponse response);
	IBasicCrudService<T, ID> getService();
}
