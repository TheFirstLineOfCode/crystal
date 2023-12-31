package com.thefirstlineofcode.crystal.bootstrap;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class CrystalApplicationImpl extends SpringApplication {
	private Path applicationHome;
	
	public CrystalApplicationImpl(Path applicationHome, Class<?>... primarySources) {
		this(applicationHome, null, primarySources);
	}
	
	public CrystalApplicationImpl(Path applicationHome, ResourceLoader resourceLoader, Class<?>... primarySources) {
		super(resourceLoader, primarySources);
		
		this.applicationHome = applicationHome;
	}

	public static ConfigurableApplicationContext run(Path applicationHome, Class<?> primarySource, String... args) {
		return run(applicationHome, new Class<?>[] {primarySource}, args);
	}
	
	public static ConfigurableApplicationContext run(Path applicationHome, Class<?>[] primarySources, String[] args) {
		return new CrystalApplicationImpl(applicationHome, primarySources).run(args);
	}
	
	@Override
	public ConfigurableApplicationContext run(String... args) {
		return super.run(args);
	}
	
	public Path getApplicationHome() {
		return applicationHome;
	}
}
