package com.thefirstlineofcode.crystal.plugins.react.admin;

import java.util.ArrayList;
import java.util.List;

import org.pf4j.PluginManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thefirstlineofcode.crystal.framework.IPluginManagerAware;
import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;
import com.thefirstlineofcode.crystal.framework.ui.CrudViews;
import com.thefirstlineofcode.crystal.framework.ui.CustomView;
import com.thefirstlineofcode.crystal.framework.ui.Menu;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UiConfigurationController implements IPluginManagerAware, ApplicationContextAware, InitializingBean {
	private List<CrudViews> crudViewses;
	private List<CustomView> customViews;
	private List<Menu> menus;
	
	private UiConfiguration uiConfiguration;
	
	@GetMapping("/ui-configuration")
	public UiConfiguration getUiConfiguration() {
		return uiConfiguration;
	}

	private List<Resource> generateResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		crudViewses = new ArrayList<>();
		customViews = new ArrayList<>();
		
		String[] restControllerBeanNames = applicationContext.getBeanNamesForAnnotation(RestController.class);
		for (String restControllerBeanName : restControllerBeanNames) {
			Class<?> restControllerClass = applicationContext.getType(restControllerBeanName);
			
			CrudViews[] someCrudViewses = restControllerClass.getAnnotationsByType(CrudViews.class);
			if (someCrudViewses != null && someCrudViewses.length > 0) {
				for (CrudViews crudViews : someCrudViewses) {
					crudViewses.add(crudViews);
				}
			}
			
			CustomView[] someCustomViews = restControllerClass.getAnnotationsByType(CustomView.class);
			if (someCustomViews != null && someCustomViews.length > 0) {
				for (CustomView customView : someCustomViews) {
					customViews.add(customView);
				}
			}
		}
	}

	@Override
	public void setPluginManager(PluginManager pluginManager) {
		menus = new ArrayList<>();
		
		List<Class<? extends ISpringConfiguration>> contributedSpringConfigurationClasses =
				pluginManager.getExtensionClasses(ISpringConfiguration.class);
		if (contributedSpringConfigurationClasses == null || contributedSpringConfigurationClasses.size() == 0) {
			return;
		}
		
		for (Class<?> contributedSpringConfigurationClass : contributedSpringConfigurationClasses) {
			Menu[] someMenus = contributedSpringConfigurationClass.getAnnotationsByType(Menu.class);
			
			for (Menu menu : someMenus) {
				menus.add(menu);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		uiConfiguration = new UiConfiguration();
		uiConfiguration.setResources(generateResources());
	}
}
