package com.thefirstlineofcode.crystal.plugins.simple.auth;

import org.pf4j.Extension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;

@Extension
@Configuration
@ComponentScan
public class AuthConfiguration implements ISpringConfiguration {}
