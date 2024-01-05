package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

public interface IBasicCrudController<T> {
	public List<T> getList(HttpServletRequest request, HttpHeaders httpHeaders,
			Map<String, String> requestParameters, HttpServletResponse response);
	IBasicCrudService<T> getService();
}
