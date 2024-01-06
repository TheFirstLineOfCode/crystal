package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;

import com.thefirstlineofcode.crystal.framework.data.ListQueryParams;

public interface IBasicCrudService<T, ID> {
	List<T> getList(ListQueryParams listQueryParams);
	long getTotal(ListQueryParams listQueryParams);
	T getOne(ID id);
	List<T> getMany(String[] ids);
}
