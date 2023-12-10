package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BasicCrudController<T> implements IBasicCrudController<T>, ICrudHttpReqeustAdapterAware {
	protected ICrudHttpRequestAdapter crudHttpRequestAdapter;
	
	@GetMapping
	public List<T> getList(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam Map<String, String> requestParameters) {
		return doGetList(crudHttpRequestAdapter.getPageRequest(httpHeaders, requestParameters));
	}
	
	protected List<T> doGetList(PageRequest pageRequest) {
		return getService().getList(pageRequest);
	}
	
	@Override
	public void setCrudHttpReqeustAdapter(ICrudHttpRequestAdapter crudHttpRequestAdapter) {
		this.crudHttpRequestAdapter = crudHttpRequestAdapter;
	}
}
