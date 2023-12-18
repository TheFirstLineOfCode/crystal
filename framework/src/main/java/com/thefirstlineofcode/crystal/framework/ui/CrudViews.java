package com.thefirstlineofcode.crystal.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CrudViews {
	String name();
	String listViewComponentName() default "";
	String createViewComponentName() default "";
	String editViewComponentName() default "";
	Menu menu();
}
