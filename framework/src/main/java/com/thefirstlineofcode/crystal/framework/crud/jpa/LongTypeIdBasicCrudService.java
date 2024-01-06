package com.thefirstlineofcode.crystal.framework.crud.jpa;

public abstract class LongTypeIdBasicCrudService<T> extends BasicCrudService<T, Long> {
	@Override
	protected Long[] getIds(String[] sIds) {
		Long[] ids = new Long[sIds.length];
		
		for (int i = 0; i < sIds.length; i++) {
			ids[i] = Long.valueOf(sIds[i]);
		}
		
		return ids;
	}
}
