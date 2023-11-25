package com.thefirstlineofcode.crystal.framework.data;

public interface IIdProvider<T> {
	void setId(T id);
	T getId();
}
