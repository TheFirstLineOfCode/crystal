package com.thefirstlineofcode.crystal.framework.ui.reactadmin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.thefirstlineofcode.crystal.framework.ui.BootMenu;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomView {
	String name();
	String viewName();
	BootMenu menu();
}
