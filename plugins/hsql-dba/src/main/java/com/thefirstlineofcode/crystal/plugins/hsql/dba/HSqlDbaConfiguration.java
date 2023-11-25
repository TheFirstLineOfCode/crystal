package com.thefirstlineofcode.crystal.plugins.hsql.dba;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.pf4j.Extension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.thefirstlineofcode.crystal.framework.ISpringConfiguration;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationProperties;
import com.thefirstlineofcode.crystal.framework.config.IConfigurationPropertiesAware;

@Extension
@Configuration
@EnableTransactionManagement
public class HSqlDbaConfiguration implements ISpringConfiguration, IConfigurationPropertiesAware {
	private static final int DEFAULT_HSQL_PORT = 9001;
	
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
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

	    return transactionManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		// entityManagerFactoryBean.setPackagesToScan("com.thefirstlineofcode.crystal.plugins.auth");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(getJpaProperties());
		
		return entityManagerFactoryBean;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	Properties getJpaProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("spring.jpa.show-sql", "true");
		properties.setProperty("spring.jpa.properties.hibernate.format_sql", "true");
		properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.setProperty("spring.jpa.properties.hibernate.current_session_context_class", "thread");
		properties.setProperty("spring.jpa.hibernate.ddl-auto", "update");		
		
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
}
