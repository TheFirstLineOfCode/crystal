package com.thefirstlineofcode.crystal.framework.crud;

import java.util.Map;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

public interface ICrudHttpRequestAdapter extends ExtensionPoint {
	PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters);
}
