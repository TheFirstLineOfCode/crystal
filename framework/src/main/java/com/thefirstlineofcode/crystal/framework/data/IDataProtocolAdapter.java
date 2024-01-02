package com.thefirstlineofcode.crystal.framework.data;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

public interface IDataProtocolAdapter extends ExtensionPoint {
	PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters);
	public void prepareResponse(HttpServletResponse response, PageRequest pageRequest, IBasicCrudService<?> service);
}
