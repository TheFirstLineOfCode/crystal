package com.thefirstlineofcode.crystal.framework;


import java.nio.file.Path;

import org.pf4j.BasePluginLoader;
import org.pf4j.ClassLoadingStrategy;
import org.pf4j.PluginClassLoader;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginManager;

public class CrystalDevelopmentPlguinLoader extends BasePluginLoader {
	public CrystalDevelopmentPlguinLoader(PluginManager pluginManager) {
		super(pluginManager, new CrystalDevelopmentPluginClasspath());
	}
	
	public ClassLoader loadPlugin(Path pluginPath, PluginDescriptor pluginDescriptor) {
		PluginClassLoader pluginClassLoader = new CrystalPluginClassLoader(pluginManager, pluginDescriptor,
				getClass().getClassLoader(), ClassLoadingStrategy.ADP);
		loadClasses(pluginPath, pluginClassLoader);
		loadJars(pluginPath, pluginClassLoader);
		
		return pluginClassLoader;
	}
}
