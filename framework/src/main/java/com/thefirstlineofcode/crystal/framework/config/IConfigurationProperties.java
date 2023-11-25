package com.thefirstlineofcode.crystal.framework.config;

public interface IConfigurationProperties {	
	Boolean getBoolean(String name);
	Boolean getBoolean(String name, Boolean defaultValue);
	Integer getInteger(String name);
	Integer getInteger(String name, Integer defaultValue);
	String getString(String name);
	String getString(String name, String defaultValue);
	
	String[] getPropertyNames();
}
