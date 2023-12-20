package com.thefirstlineofcode.crystal.examples.application;

import org.pf4j.Extension;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;
import com.thefirstlineofcode.crystal.framework.ui.StructuralMenu;

@Extension
@Configuration
@StructuralMenu(name = "tools", label = "menu_name_tools", priority = StructuralMenu.PRIORITY_MEDIUM)
@StructuralMenu(name = "system", label = "menu_name_system", priority = StructuralMenu.PRIORITY_LOW)
public class ExampleApplicaionConfiguration implements ISpringConfiguration {}
