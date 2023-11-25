package com.thefirstlineofcode.crystal.framework;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.thefirstlineofcode.crystal.framework.config.CrystalApplicationProperties;

@Configuration
@EnableConfigurationProperties(CrystalApplicationProperties.class)
public class FrameworkConfiguration {}
