package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;

import org.springframework.data.domain.PageRequest;

public interface IBasicCrudService<T> {
	List<T> getList(PageRequest pageRequest);
	long getTotal();
}
