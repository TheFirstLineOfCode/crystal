package com.thefirstlineofcode.crystal.framework.crud.jpa;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thefirstlineofcode.crystal.framework.crud.IBasicCrudService;

public abstract class BasicCrudService<T, ID> implements IBasicCrudService<T> {

	@Override
	public List<T> getList(PageRequest pageRequest) {
		return getRepository().findAll(getPageableFromPageRequest(pageRequest)).toList();
	}
	
	@Override
	public long getTotal() {
		return getRepository().count();
	}
	
	protected Pageable getPageableFromPageRequest(PageRequest pageRequest) {
		return pageRequest;
	}
	
	protected abstract PagingAndSortingRepository<T, ID> getRepository();
}
