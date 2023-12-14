package com.thefirstlineofcode.crystal.plugins.hsql.jpa.dba;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.pf4j.Extension;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationProperties;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationPropertiesAware;

@Extension
@Configuration
@EnableTransactionManagement
public class HSqlJpaDbaConfiguration implements ISpringConfiguration,
			IConfigurationPropertiesAware, ApplicationContextAware {
	private static final String PACKAGE_NAME_CRYSTAL_FRAMEWORK = "com.thefirstlineofcode.crystal.framework";

	private static final int DEFAULT_HSQL_PORT = 9001;
	
	private ApplicationContext appContext;
	private int hSqlPort;
	
	@Bean
	public HSqlServer hSqlServer() {
		return new HSqlServer(hSqlPort);
	}
	
	@Bean
	@DependsOn("hSqlServer")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl(String.format("jdbc:hsqldb:hsql://localhost:%s/crystal", hSqlPort));
		dataSource.setUsername("SA");
		dataSource.setPassword("");
		
		try {
			dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Can't create data source.", e);
		}
		
		return dataSource;
	}
	
	@Bean
	@DependsOn("entityManagerFactory")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory);

	    return transactionManager;
	}
	
	@Bean
	@DependsOn("dataSource")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(getEntityScanPackages());
		entityManagerFactoryBean.setJpaVendorAdapter(getHibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(getJpaProperties());
		
		return entityManagerFactoryBean;
	}

	private HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		
		hibernateJpaVendorAdapter.setShowSql(true);
		/*hibernateJpaVendorAdapter.set(true);
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setShowSql(true);*/
		
		
		return hibernateJpaVendorAdapter;
	}
	
	private String[] getEntityScanPackages() {
		List<String> packages = EntityScanPackages.get(appContext).getPackageNames();
		if (packages == null || packages.size() == 0)
			return new String[] {PACKAGE_NAME_CRYSTAL_FRAMEWORK};
		
		return packages.toArray(new String[packages.size()]);
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	Properties getJpaProperties() {
		Properties properties = new Properties();
		
		properties.setProperty(AvailableSettings.FORMAT_SQL, "true");
		properties.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		
		return properties;
	}
	
/*	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		
		List<IDataContributor> dataContributors = appComponentService.getPluginManager().getExtensions(IDataContributor.class);
		for (IDataContributor dataContributor : dataContributors) {
			URL[] initScripts = dataContributor.getInitScripts();
			if (initScripts == null || initScripts.length == 0)
				continue;
			
			for (URL initScript : initScripts) {
				resourceDatabasePopulator.addScript(new UrlResource(initScript));			
			}
		}
		resourceDatabasePopulator.setContinueOnError(true);
		
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		
		return dataSourceInitializer;
	}*/
	
	@Override
	public void setConfigurationProperties(IConfigurationProperties properties) {
		hSqlPort = properties.getInteger("hSqlPort", DEFAULT_HSQL_PORT);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;
	}
}
