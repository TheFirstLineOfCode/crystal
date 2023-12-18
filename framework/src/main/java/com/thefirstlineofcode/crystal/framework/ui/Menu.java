package com.thefirstlineofcode.crystal.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatableMenus.class)
public @interface Menu {
	public static final int PRIORITY_HIGH = 2000;
	public static final int PRIORITY_MEDIUM = 1000;
	public static final int PRIORITY_LOW = 200;
	
	String parent() default "";
	boolean leaf() default true;
	String name() default "";
	String label() default "";
	String icon() default "";
	int priority() default PRIORITY_MEDIUM;
}
