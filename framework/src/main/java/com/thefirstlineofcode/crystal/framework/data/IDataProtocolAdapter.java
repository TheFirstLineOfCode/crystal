package com.thefirstlineofcode.crystal.framework.data;

import java.util.Map;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

public interface IDataProtocolAdapter extends ExtensionPoint {
	PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters);
}
