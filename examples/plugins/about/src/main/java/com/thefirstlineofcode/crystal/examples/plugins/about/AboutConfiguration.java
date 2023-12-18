package com.thefirstlineofcode.crystal.examples.plugins.about;

import org.pf4j.Extension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;

@Extension
@Configuration
@ComponentScan
public class AboutConfiguration implements ISpringConfiguration {}
