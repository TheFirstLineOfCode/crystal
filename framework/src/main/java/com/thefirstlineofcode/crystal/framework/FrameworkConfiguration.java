package com.thefirstlineofcode.crystal.framework;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import com.thefirstlineofcode.crystal.framework.config.CrystalApplicationProperties;

@Configuration
@EnableConfigurationProperties(CrystalApplicationProperties.class)
public class FrameworkConfiguration implements WebMvcConfigurer {
	private static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true).allowedMethods(ORIGINS).maxAge(3600);
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUrlPathHelper(new UrlPathHelper() {
			@Override
			public String getPathWithinApplication(HttpServletRequest request) {
				String path = super.getPathWithinApplication(request);
				
				int woocommercePathPrefixStart = path.indexOf("/wp-json/wc/v3");
				if (woocommercePathPrefixStart == -1)
					return path;
				
				return path.substring(0, woocommercePathPrefixStart) +
					path.substring(woocommercePathPrefixStart + 1);

			}
		});
	}
}
