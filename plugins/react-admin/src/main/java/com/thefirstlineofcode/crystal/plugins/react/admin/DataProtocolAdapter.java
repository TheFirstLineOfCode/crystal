package com.thefirstlineofcode.crystal.plugins.react.admin;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.pf4j.Extension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;

import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;
import com.thefirstlineofcode.crystal.framework.data.IDataProtocolAdapter;

@Extension
public class DataProtocolAdapter implements IDataProtocolAdapter {
	private static final String PARAM_PAGE = "page";
	private static final String PARAM_PER_PAGE = "per_page";
	// private static final String PARAM_ORDER = "order";
	
	
	@Override
	public PageRequest getPageRequest(HttpHeaders httpHeaders, Map<String, String> requestParameters) {
		int page = getPage(requestParameters);
		
		int size = getSize(requestParameters);
		
		Order order = getOrder(requestParameters);
		
		if (order == null) {			
			return PageRequest.of(page, size);
		} else {
			return PageRequest.of(page, size, Sort.by(order));
		}
	}


	private Order getOrder(Map<String, String> requestParameters) {
		// ra-data-woocommerce bug????: No 'field' parameter in request parameters .
		// TODO
		return null;
	}


	private int getSize(Map<String, String> requestParameters) {
		int size = 10;
		String sPerPage = requestParameters.get(PARAM_PER_PAGE);
		if (sPerPage != null) {			
			try {
				size = Integer.parseInt(sPerPage);
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}
		
		if (size <= 0)
			size = 10;
		
		return size;
	}


	private int getPage(Map<String, String> requestParameters) {
		int page = 0;
		String sPage = requestParameters.get(PARAM_PAGE);
		if (sPage != null) {			
			try {
				page = Integer.parseInt(sPage) - 1;
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}
		
		if (page < 0)
			page = 0;
		
		return page;
	}


	@Override
	public void prepareResponse(HttpServletResponse response, PageRequest pageRequest, IBasicCrudService<?> service) {
		response.addHeader("Access-Control-Expose-Headers", "X-WP-Total, X-WP-TotalPages");
		
		long total = service.getTotal();
		response.addHeader("X-WP-Total", String.valueOf(total));
		
		int pageSize = pageRequest.getPageSize();
		long totalPages = total / pageSize;
		if (total % pageSize != 0)
			totalPages += 1;
		
		response.addHeader("X-WP-TotalPages", String.valueOf(totalPages));
	}
}
