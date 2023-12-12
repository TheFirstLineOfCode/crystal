package com.thefirstlineofcode.crystal.plugins.ra.data.woocommerce.protocol.adapter;

import java.util.Map;

import org.pf4j.Extension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.IDataProtocolAdapter;

@Extension
public class DataProtocolAdapter implements IDataProtocolAdapter {

	@Override
	public PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
