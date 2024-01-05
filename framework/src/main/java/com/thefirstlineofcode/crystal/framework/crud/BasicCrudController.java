package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.thefirstlineofcode.crystal.framework.data.IDataProtocolAdapter;
import com.thefirstlineofcode.crystal.framework.data.QueryParams;

public abstract class BasicCrudController<T> implements IBasicCrudController<T>, IDataProtocolAdapterAware {
	protected IDataProtocolAdapter dataProtocolAdapter;
	
	@GetMapping
	public List<T> getList(HttpServletRequest request,
			@RequestHeader HttpHeaders httpHeaders,
			@RequestParam Map<String, String> requestParameters,
				HttpServletResponse response) {
		QueryParams pageRequest = dataProtocolAdapter.getQueryParams(request, httpHeaders, requestParameters);
		
		List<T> list = doGetList(pageRequest);
		
		dataProtocolAdapter.prepareResponse(response, pageRequest, getService());
		
		return list;
	}
	
	protected List<T> doGetList(QueryParams queryParams) {
		return getService().getList(queryParams);
	}
	
	@Override
	public void setDataProtocolAdapter(IDataProtocolAdapter dataProtocolAdapter) {
		this.dataProtocolAdapter = dataProtocolAdapter;
	}
}
