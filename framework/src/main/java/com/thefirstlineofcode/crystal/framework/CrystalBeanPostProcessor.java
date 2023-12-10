package com.thefirstlineofcode.crystal.framework;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.pf4j.PluginWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.origin.OriginTrackedValue;

import com.thefirstlineofcode.crystal.framework.config.ConfigurationProperties;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationProperties;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationPropertiesAware;
import com.thefirstlineofcode.crystal.framework.crud.ICrudHttpReqeustAdapterAware;
import com.thefirstlineofcode.crystal.framework.crud.ICrudHttpRequestAdapter;

public class CrystalBeanPostProcessor implements BeanPostProcessor {
	private static final String CRYSTAL_PLUGINS_PREFIX = "crystal.plugins";
	
	private Path applicationHome;
	private Map<String, Object> applicationProperties;
	private CrystalPluginManager pluginManager;
	private Map<String, IConfigurationProperties> pluginIdToConfigurationProperties;
	private ICrudHttpRequestAdapter crudHttpRequestAdapter;
	
	public CrystalBeanPostProcessor(Path applicationHome, Map<String, Object> applicationProperties, CrystalPluginManager pluginManager) {
		this.applicationHome = applicationHome;
		this.applicationProperties = applicationProperties;
		this.pluginManager = pluginManager;
		
		crudHttpRequestAdapter = getCrudHttpRequestAdapter();
		
		pluginIdToConfigurationProperties = new HashMap<>();
	}
	
	private ICrudHttpRequestAdapter getCrudHttpRequestAdapter() {
		List<ICrudHttpRequestAdapter> crudHttpRequestAdapters = pluginManager.getExtensions(ICrudHttpRequestAdapter.class);
		if (crudHttpRequestAdapters == null || crudHttpRequestAdapters.size() == 0)
			throw new RuntimeException("CRUD HTTP request adapter not found.");
		
		if (crudHttpRequestAdapters.size() != 1)
			throw new RuntimeException("Multiple CRUD HTTP request adapters found.");
		
		return crudHttpRequestAdapters.get(0);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof IApplicationHomeAware) {
			((IApplicationHomeAware)bean).setApplicationHome(applicationHome);
		}
		
		if (bean instanceof IPluginManagerAware) {
			((IPluginManagerAware)bean).setPluginManager(pluginManager);
		}
		
		PluginWrapper plugin = pluginManager.loadedBy(bean);
		if (plugin == null)
			return bean;
		
		if (bean instanceof IConfigurationPropertiesAware) {
			((IConfigurationPropertiesAware)bean).setConfigurationProperties(getConfigurationProperties(plugin.getPluginId()));
		}
		
		if (bean instanceof ICrudHttpReqeustAdapterAware) {
			((ICrudHttpReqeustAdapterAware)bean).setCrudHttpReqeustAdapter(crudHttpRequestAdapter);
		}
		
		return bean;
	}

	private IConfigurationProperties getConfigurationProperties(String pluginId) {
		IConfigurationProperties configurationProperties = pluginIdToConfigurationProperties.get(pluginId);
		if (configurationProperties == null) {
			synchronized (applicationProperties) {
				configurationProperties = pluginIdToConfigurationProperties.get(pluginId);
				if (configurationProperties != null)
					return configurationProperties;
				
				String pluginPropertiesPrefix = String.format("%s.%s.", CRYSTAL_PLUGINS_PREFIX, pluginId);
				Properties properties = new Properties();
				Set<String> keys = applicationProperties.keySet();
				for (String key : keys) {
					if (key.startsWith(pluginPropertiesPrefix) && key.length() > pluginPropertiesPrefix.length()) {
						String propertyName = key.substring(pluginPropertiesPrefix.length());
						String propertyValue = ((OriginTrackedValue)applicationProperties.get(key)).getValue().toString();
						
						properties.put(propertyName, propertyValue);
					}
				}
				
				configurationProperties = new ConfigurationProperties(properties);
				pluginIdToConfigurationProperties.put(pluginId, configurationProperties);
			}
		}
		
		return configurationProperties;
	}
}
