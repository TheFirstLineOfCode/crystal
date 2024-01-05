package com.thefirstlineofcode.crystal.framework.crud.jpa;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;
import com.thefirstlineofcode.crystal.framework.data.Filters;
import com.thefirstlineofcode.crystal.framework.data.QueryParams;

public abstract class BasicCrudService<T, ID> implements IBasicCrudService<T> {

	@Override
	public List<T> getList(QueryParams queryParams) {
		if (queryParams.filters.noFilters())
			return getNoFiltersList(getPageable(queryParams));
		else
			return getListByFilters(getPageable(queryParams), queryParams.filters);
	}

	protected List<T> getNoFiltersList(Pageable pageable) {
		return getRepository().findAll(pageable).toList();
	}
	
	protected List<T> getListByFilters(Pageable pageable, Filters filters) {
		throw new RuntimeException(new OperationNotSupportedException("The operation not supported yet!"));
	}
	
	@Override
	public long getTotal(QueryParams queryParams) {
		if (queryParams.filters.noFilters())
			return getRepository().count();
		else
			return getTotalByFilters(getPageable(queryParams), queryParams.filters);
	}
	
	protected long getTotalByFilters(Pageable pageable, Filters filters) {
		throw new RuntimeException(new OperationNotSupportedException("The operation not supported yet!"));
	}

	protected Pageable getPageable(QueryParams queryParams) {
		return queryParams.pageable;
	}
	
	protected abstract PagingAndSortingRepository<T, ID> getRepository();
}
