package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
			@RequestParam Map<String, String> requestParameters,
				HttpServletResponse response) {
		PageRequest pageRequest = dataProtocolAdapter.getPageRequest(httpHeaders, requestParameters);
		
		List<T> list = doGetList(pageRequest);
		
		dataProtocolAdapter.prepareResponse(response, pageRequest, getService());
		
		return list;
	}
	
	protected List<T> doGetList(PageRequest pageRequest) {
		return getService().getList(pageRequest);
	}
	
	@Override
	public void setDataProtocolAdapter(IDataProtocolAdapter dataProtocolAdapter) {
		this.dataProtocolAdapter = dataProtocolAdapter;
	}
}
