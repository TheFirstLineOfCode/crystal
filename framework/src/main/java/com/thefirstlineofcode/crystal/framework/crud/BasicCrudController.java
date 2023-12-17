package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.thefirstlineofcode.crystal.framework.data.IDataProtocolAdapter;

public abstract class BasicCrudController<T> implements IBasicCrudController<T>, IDataProtocolAdapterAware {
	protected IDataProtocolAdapter dataProtocolAdapter;
	
	@GetMapping
	public List<T> getList(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam Map<String, String> requestParameters) {
		return doGetList(dataProtocolAdapter.getPageRequest(httpHeaders, requestParameters));
	}
	
	protected List<T> doGetList(PageRequest pageRequest) {
		return getService().getList(pageRequest);
	}
	
	@Override
	public void setDataProtocolAdapter(IDataProtocolAdapter dataProtocolAdapter) {
		this.dataProtocolAdapter = dataProtocolAdapter;
	}
}
