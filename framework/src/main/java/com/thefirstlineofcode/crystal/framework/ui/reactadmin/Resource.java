package com.thefirstlineofcode.crystal.framework.ui.reactadmin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.thefirstlineofcode.crystal.framework.ui.BootMenu;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {
	String name();
	String recordRepresentation() default "";
	String listViewName() default "";
	String showViewName() default "";
	String createViewName() default "";
	String editViewName() default "";
	BootMenu menu();
}
