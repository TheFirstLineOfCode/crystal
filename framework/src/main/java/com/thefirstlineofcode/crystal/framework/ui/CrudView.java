package com.thefirstlineofcode.crystal.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CrudView {
	String name();
	String listComponentName() default "";
	String createComponentName() default "";
	String editComponentName() default "";
	ViewMenu menu();
}
