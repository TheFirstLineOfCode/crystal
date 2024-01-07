package com.thefirstlineofcode.crystal.framework.crud;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.data.IIdProvider;

public interface IBasicCrudController<ID, T extends IIdProvider<ID>> {
	public Object getResources(HttpServletRequest request, HttpHeaders httpHeaders,
			Map<String, String> requestParameters, HttpServletResponse response);
	public T getResource(ID id);
	public T updateResource(ID id, T updated);
	public void deleteResource(ID id);
	IBasicCrudService<ID, T> getService();
}
