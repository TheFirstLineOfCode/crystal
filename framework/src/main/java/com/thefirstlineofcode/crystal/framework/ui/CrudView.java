package com.thefirstlineofcode.crystal.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CrudView {
	String name();
	String listViewName() default "";
	String createViewName() default "";
	String editViewName() default "";
	ViewMenu menu();
}
