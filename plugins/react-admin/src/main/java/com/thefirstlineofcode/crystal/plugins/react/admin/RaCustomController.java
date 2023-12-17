package com.thefirstlineofcode.crystal.plugins.react.admin;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class RaCustomController<T> implements IRaCustomController<T> {
	@GetMapping
	public T getList() {
		return processInitialRequest();
	}

	protected abstract T processInitialRequest();
}
