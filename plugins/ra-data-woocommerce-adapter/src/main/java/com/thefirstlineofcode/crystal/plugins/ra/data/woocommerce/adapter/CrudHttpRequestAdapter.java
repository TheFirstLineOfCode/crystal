package com.thefirstlineofcode.crystal.plugins.ra.data.woocommerce.adapter;

import java.util.Map;

import org.pf4j.Extension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.ICrudHttpRequestAdapter;

@Extension
public class CrudHttpRequestAdapter implements ICrudHttpRequestAdapter {

	@Override
	public PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
