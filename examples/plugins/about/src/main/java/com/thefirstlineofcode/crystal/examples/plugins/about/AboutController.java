package com.thefirstlineofcode.crystal.examples.plugins.about;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.framework.config.IConfigurationProperties;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationPropertiesAware;
import com.thefirstlineofcode.crystal.framework.ui.BootMenu;
import com.thefirstlineofcode.crystal.framework.ui.reactadmin.CustomView;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@CustomView(name = "about", viewName = "AboutView",
	menu = @BootMenu(parent = "help", label = "ca.title.about", priority = BootMenu.PRIORITY_LOW))
public class AboutController implements IConfigurationPropertiesAware {
	private About about;
	
	@GetMapping("/about")
	public About getAbout() {
		return about;
	}
	
	@Override
	public void setConfigurationProperties(IConfigurationProperties properties) {
		about = new About();
		
		about.setApplicationName(properties.getString("applicationName", "Unknown application"));
		about.setVersion(properties.getString("version", "Unknown"));
		about.setDeveloper(properties.getString("developer", "Unknown"));
	}
}
