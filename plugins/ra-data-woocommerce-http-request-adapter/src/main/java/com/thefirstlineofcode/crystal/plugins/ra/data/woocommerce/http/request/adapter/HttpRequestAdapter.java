package com.thefirstlineofcode.crystal.plugins.ra.data.woocommerce.http.request.adapter;

import java.util.Map;

import org.pf4j.Extension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.IHttpRequestAdapter;

@Extension
public class HttpRequestAdapter implements IHttpRequestAdapter {

	@Override
	public PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
