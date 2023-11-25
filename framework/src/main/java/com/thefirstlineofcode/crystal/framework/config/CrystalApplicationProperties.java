package com.thefirstlineofcode.crystal.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("crystal.application")
public class CrystalApplicationProperties {
	private String[] disabledPlugins;

	public String[] getDisabledPlugins() {
		return disabledPlugins;
	}

	public void setDisabledPlugins(String[] disabledPlugins) {
		this.disabledPlugins = disabledPlugins;
	}
}
