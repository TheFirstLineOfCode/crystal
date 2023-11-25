package com.thefirstlineofcode.crystal.examples.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.thefirstlineofcode.crystal.bootstrap.CrystalApplication;

@SpringBootApplication
@ComponentScan
public class ExamplesApplication {
	public static void main(String[] args) {
		CrystalApplication.run(new Class<?>[] {
			ExamplesApplication.class
		}, args);
	}
}