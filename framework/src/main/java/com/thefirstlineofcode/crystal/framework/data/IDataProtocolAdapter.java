package com.thefirstlineofcode.crystal.framework.data;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pf4j.ExtensionPoint;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

public interface IDataProtocolAdapter extends ExtensionPoint {
	boolean isGetOneRequest(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	boolean isGetManyRequest(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	boolean isGetListRequest(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	boolean isGetManyReferenceRequest(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	ListQueryParams getListQueryParams(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	String[] getManyIds(HttpServletRequest request, HttpHeaders httpHeaders, Map<String, String> requestParameters);
	public void prepareResponse(HttpServletResponse response, ListQueryParams queryParams, IBasicCrudService<?, ?> service);
}
