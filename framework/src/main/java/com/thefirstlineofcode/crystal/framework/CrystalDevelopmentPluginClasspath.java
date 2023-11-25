package com.thefirstlineofcode.crystal.framework;

import org.pf4j.PluginClasspath;

public class CrystalDevelopmentPluginClasspath extends PluginClasspath {
	public CrystalDevelopmentPluginClasspath() {
		addClassesDirectories("target/classes");
		addJarsDirectories("target/dependency");
	}
}
