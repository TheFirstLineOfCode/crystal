package com.thefirstlineofcode.crystal.framework.crud;

import java.util.List;

import com.thefirstlineofcode.crystal.framework.data.QueryParams;

public interface IBasicCrudService<T> {
	List<T> getList(QueryParams queryParams);
	long getTotal(QueryParams queryParams);
}
