package com.thefirstlineofcode.crystal.framework;

import org.pf4j.ExtensionPoint;

public interface ISchemaInitializer extends ExtensionPoint {
	String getInitialScript();
}
