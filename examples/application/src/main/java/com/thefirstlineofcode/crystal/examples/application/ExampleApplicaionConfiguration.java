package com.thefirstlineofcode.crystal.examples.application;

import org.pf4j.Extension;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;
import com.thefirstlineofcode.crystal.framework.ui.Menu;

@Extension
@Configuration
@Menu(name = "tools", label = "menu_name_tools", leaf = false, priority = Menu.PRIORITY_MEDIUM)
@Menu(name = "system", label = "menu_name_system", leaf = false, priority = Menu.PRIORITY_LOW)
public class ExampleApplicaionConfiguration implements ISpringConfiguration {}
