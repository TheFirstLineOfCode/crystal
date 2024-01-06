package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.thefirstlineofcode.crystal.framework.data.IDataProtocolAdapter;
import com.thefirstlineofcode.crystal.framework.data.ListQueryParams;

public abstract class BasicCrudController<T, ID> implements IBasicCrudController<T, ID>, IDataProtocolAdapterAware {
	protected IDataProtocolAdapter dataProtocolAdapter;
	
	@GetMapping
	public List<T> getResources(HttpServletRequest request,
			@RequestHeader HttpHeaders httpHeaders,
				@RequestParam Map<String, String> requestParameters,
					HttpServletResponse response) {
		List<T> list;
		if (dataProtocolAdapter.isGetListRequest(request, httpHeaders, requestParameters)) {
			ListQueryParams listQueryParams = dataProtocolAdapter.getListQueryParams(request, httpHeaders, requestParameters);
			
			list = doGetList(listQueryParams);
			
			dataProtocolAdapter.prepareResponse(response, listQueryParams, getService());
		} else if (dataProtocolAdapter.isGetManyRequest(request, httpHeaders, requestParameters)) {
			String[] ids = dataProtocolAdapter.getManyIds(request, httpHeaders, requestParameters);
			list = doGetMany(ids);
		} else if (dataProtocolAdapter.isGetManyReferenceRequest(request, httpHeaders, requestParameters)) {
			// TODO
			throw new RuntimeException("Not implemented yet!");
		} else {
			// TODO
			throw new RuntimeException("Not implemented yet!");
		}
		
		return list;
	}
	
	private List<T> doGetMany(String[] ids) {
		return getService().getMany(ids);
	}
	
	@GetMapping(value = "/{id}")
	public T getResource(@PathVariable("id") ID id) {
		return getService().getOne(id);
	}
	
	protected List<T> doGetList(ListQueryParams listQueryParams) {
		return getService().getList(listQueryParams);
	}
	
	@Override
	public void setDataProtocolAdapter(IDataProtocolAdapter dataProtocolAdapter) {
		this.dataProtocolAdapter = dataProtocolAdapter;
	}
}
