package com.thefirstlineofcode.crystal.plugins.react.admin;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.thefirstlineofcode.crystal.framework.ui.CrudView;
import com.thefirstlineofcode.crystal.framework.ui.CustomView;
import com.thefirstlineofcode.crystal.framework.ui.StructuralMenu;
import com.thefirstlineofcode.crystal.framework.ui.ViewMenu;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UiConfigurationController implements IPluginManagerAware, ApplicationContextAware, InitializingBean {
	private List<CrudView> crudViews;
	private List<CustomView> customViews;
	private List<Menu> menus;
	
	private UiConfiguration uiConfiguration;
	
	@GetMapping("/ui-configuration")
	public UiConfiguration getUiConfiguration() {
		return uiConfiguration;
	}

	private List<Resource> generateResources() {
		return generateResources(getMenuAndViews(null));
	}

	private List<Resource> generateResources(List<MenuAndView> menuAndViews) {
		List<Resource> resources = new ArrayList<>();
		
		for (MenuAndView menuAndView : menuAndViews) {
			resources.add(getResource(menuAndView));
		}
		
		return resources;
	}

	private Resource getResource(MenuAndView menuAndView) {
		Menu menu = menuAndView.menu;
		Resource resource = new Resource(menu.name, menu.label, !menu.leaf, "".equals(menu.parent) ? null : menu.parent);
		
		if (menuAndView.view == null) {
			// NOOP
		} else if (menuAndView.view instanceof CrudView) {
			CrudView crudView = (CrudView)menuAndView.view;
			resource.setListViewName(getNullableComponentName(crudView.listViewName()));
			resource.setCreateViewName(getNullableComponentName(crudView.createViewName()));
			resource.setEditViewName(getNullableComponentName(crudView.editViewName()));
		} else if (menuAndView.view instanceof CustomView) {
			CustomView customView = (CustomView)menuAndView.view;
			resource.setListViewName(customView.viewName());
		} else {
			throw new RuntimeException(String.format("Error: Unknown type of view. View class: '%s'.", menuAndView.view.getClass()));
		}
		
		return resource;
	}

	private String getNullableComponentName(String componentName) {
		return "".equals(componentName) ? null : componentName;
	}

	private List<MenuAndView> getMenuAndViews(MenuAndView parent) {
		List<MenuAndView> children = findChildren(parent);
		
		
		List<MenuAndView> allMenuAndViews = new ArrayList<>();
		for (int i = 0; i < children.size(); i++) {
			MenuAndView child = children.get(i);
			
			if (child.menu.leaf) {
				allMenuAndViews.add(child);
			} else {
				List<MenuAndView> descendants = findDescendants(child);
				if (!noLeafMenu(descendants)) {
					allMenuAndViews.add(child);
					allMenuAndViews.addAll(descendants);
				}				
			}
		}
		
		return allMenuAndViews;
	}

	private boolean noLeafMenu(List<MenuAndView> descendants) {
		for (MenuAndView descendant : descendants) {
			if (descendant.menu.leaf)
				return false;
		}
		
		return true;
	}

	private List<MenuAndView> findChildren(MenuAndView parent) {
		String parentMenuName = parent == null ? "" : parent.menu.name;
		
		List<MenuAndView> children = new ArrayList<>();
		for (Menu menu : menus) {
			if (parentMenuName.equals(menu.parent))
				children.add(new MenuAndView(menu));
		}
		
		for (CrudView crudView : crudViews) {
			if (parentMenuName.equals(crudView.menu().parent()))
				children.add(new MenuAndView(getMenu(crudView.name(), crudView.menu()), crudView));
		}
		
		for (CustomView customView : customViews) {
			if (parentMenuName.equals(customView.menu().parent()))
				children.add(new MenuAndView(getMenu(customView.name(), customView.menu()), customView));
		}
		
		children.sort(new Comparator<MenuAndView>() {
			@Override
			public int compare(MenuAndView menuAndView1, MenuAndView menuAndView2) {
				return menuAndView2.menu.priority - menuAndView1.menu.priority;
			}
		});
		
		return children;
	}
	
	private List<MenuAndView> findDescendants(MenuAndView ancestor) {
		List<MenuAndView> descendants = new ArrayList<>();
		
		List<MenuAndView> children = findChildren(ancestor);
		for (int i = 0; i < children.size(); i++) {
			MenuAndView child = children.get(i);
			descendants.add(child);
			descendants.addAll(findDescendants(child));
		}
		
		return descendants;
	}

	private Menu getMenu(String viewName, ViewMenu viewMenu) {
		return new Menu(viewName, 
				viewMenu.label() != null ? viewMenu.label() : viewName,
				"".equals(viewMenu.parent()) ? null : viewMenu.parent(),
				true, viewMenu.priority());
	}
	
	private class MenuAndView {
		public Menu menu;
		public Object view;
		
		public MenuAndView(Menu menu) {
			this(menu, null);
		}
		
		public MenuAndView(Menu menu, Object view) {
			this.menu = menu;
			this.view = view;
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		crudViews = new ArrayList<>();
		customViews = new ArrayList<>();
		
		String[] restControllerBeanNames = applicationContext.getBeanNamesForAnnotation(RestController.class);
		for (String restControllerBeanName : restControllerBeanNames) {
			Class<?> restControllerClass = applicationContext.getType(restControllerBeanName);
			
			CrudView[] someCrudViews = restControllerClass.getAnnotationsByType(CrudView.class);
			if (someCrudViews != null && someCrudViews.length > 0) {
				for (CrudView crudView : someCrudViews) {
					crudViews.add(crudView);
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
			StructuralMenu[] someMenus = contributedSpringConfigurationClass.getAnnotationsByType(StructuralMenu.class);
			
			for (StructuralMenu menu : someMenus) {
				menus.add(getMenu(menu));
			}
		}
	}

	private Menu getMenu(StructuralMenu menu) {
		return new Menu(menu.name(),
				menu.label() != null ? menu.label() : menu.name(),
				menu.parent(), false, menu.priority());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		uiConfiguration = new UiConfiguration();
		uiConfiguration.setResources(generateResources());
	}
	
	private class Menu {
		public String name;
		public String label;
		public String parent;
		public boolean leaf;
		public int priority;
		
		public Menu(String name, String label, String parent, boolean leaf, int priority) {
			this.name = name;
			this.label = label;
			this.parent = parent;
			this.leaf = leaf;
			this.priority = priority;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Menu))
				return false;
			
			Menu other = (Menu)obj;
			if (name != other.name)
				return false;
			
			if (parent == null)
				return other.parent == null;
			
			return parent.equals(other.parent);
		}
	}
}
