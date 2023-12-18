package com.thefirstlineofcode.crystal.plugins.react.admin;

import java.util.ArrayList;
import java.util.List;

public class UiConfiguration {
	private List<Resource> resources;
	
	public UiConfiguration() {
		resources = new ArrayList<>();
	}
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}
