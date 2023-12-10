package com.thefirstlineofcode.crystal.examples.plugins.configuration.properties;

import org.pf4j.Extension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;

@Extension
@Configuration
public class ConfigurationPropertiesConfiguration implements ISpringConfiguration {
	@Bean
	public ConfigurationPropertiesBean configurationPropertiesBean() {
		return new ConfigurationPropertiesBean();
	}
}
