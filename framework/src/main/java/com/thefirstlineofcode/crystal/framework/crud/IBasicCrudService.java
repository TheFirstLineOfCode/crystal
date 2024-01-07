package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;

import com.thefirstlineofcode.crystal.framework.data.IIdProvider;
import com.thefirstlineofcode.crystal.framework.data.ListQueryParams;

public interface IBasicCrudService<ID, T extends IIdProvider<ID>> {
	List<T> getList(ListQueryParams listQueryParams);
	long getTotal(ListQueryParams listQueryParams);
	T getOne(ID id);
	List<T> getMany(String[] ids);
	T update(T t);
	void deleteById(ID id);
}
