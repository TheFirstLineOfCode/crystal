package com.thefirstlineofcode.crystal.bootstrap;

import java.net.URL;
import java.nio.file.Path;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class CrystalApplication {
	private static final String PROPERTY_CRYSTAL_LOGS_DIR = "crystal.logs.dir";
	private static final String DIRECTORY_NAME_LOGS = "logs";
	
	private ResourceLoader resourceLoader;
	private Class<?>[] primarySources;
	
	public CrystalApplication(Class<?>... primarySources) {
		this(null, primarySources);
	}
	
	public CrystalApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		this.resourceLoader = resourceLoader;
		this.primarySources = primarySources;
	}

	public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
		return run(new Class<?>[] {primarySource}, args);
	}
	
	public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
		return new CrystalApplication(primarySources).run(args);
	}
	
	public ConfigurableApplicationContext run(String... args) {
		Path applicationHome = getCrystalApplicationHome();
		System.setProperty(PROPERTY_CRYSTAL_LOGS_DIR, applicationHome.resolve(DIRECTORY_NAME_LOGS).toString());
		
		return new CrystalApplicationImpl(applicationHome, resourceLoader, primarySources).run(args);
	}
	
	public Path getCrystalApplicationHome() {
		URL applicationUrl = getClass().getProtectionDomain().getCodeSource().getLocation();
		String applicationUrlPath = applicationUrl.getPath();
		int dotJarPortStart = applicationUrlPath.indexOf(".jar!/BOOT-INF");
		
		return Path.of(applicationUrlPath.substring("file:/".length(), dotJarPortStart + 4)).getParent();
	}
}
