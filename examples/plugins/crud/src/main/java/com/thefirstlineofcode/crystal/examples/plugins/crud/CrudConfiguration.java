package com.thefirstlineofcode.crystal.examples.plugins.crud;

import org.pf4j.Extension;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;

@Extension
@Configuration
@ComponentScan
@EntityScan
public class CrudConfiguration implements ISpringConfiguration {}
