package com.thefirstlineofcode.crystal.examples.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.config.CrystalApplicationProperties;

@Configuration
public class TestApplicationProperties {
	@Autowired
	private CrystalApplicationProperties applicationProperties;

	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
	
	public class TestBean {
		@PostConstruct
		public void test() {
			String[] disabledPlugins = applicationProperties.getDisabledPlugins();
			if (disabledPlugins == null || disabledPlugins.length == 0) {
				System.out.println("No disabled plugins.");
			} else {
				for (String disabledPlugin : disabledPlugins) {					
					System.out.println(String.format("Disabled plugin: %s.", disabledPlugin));
				}
			}
		}
	}
}
