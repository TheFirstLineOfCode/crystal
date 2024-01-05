package com.thefirstlineofcode.crystal.framework.data;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pf4j.ExtensionPoint;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

public interface IDataProtocolAdapter extends ExtensionPoint {
	QueryParams getQueryParams(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	public void prepareResponse(HttpServletResponse response, QueryParams queryParams, IBasicCrudService<?> service);
}
