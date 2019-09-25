package com.shoppingcart.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class,SecurityAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages="com.shoppingcart")
public class ShoppingCartAppApplication implements WebMvcConfigurer{
	
	Logger logger = Logger.getLogger(ShoppingCartAppApplication.class);
	
	@Value("${spring.datasource.driver-class-name}")
	private String DRIVER;
 
	@Value("${spring.datasource.password}")
	private String PASSWORD;
 
	@Value("${spring.datasource.url}")
	private String URL;
 
	@Value("${spring.datasource.username}")
	private String USERNAME;
 
	@Value("${spring.jpa.database-platform}")
	private String DIALECT;
 
	@Value("${spring.jpa.show-sql}")
	private String SHOW_SQL;
 
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String HBM2DDL_AUTO;
 
	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;
 
	@Bean
	public DataSource dataSource() {
		logger.info("Adding datasource details.....");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
 
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		logger.info("Adding SessionFactory to container.....");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(hibernateProperties);
 
		return sessionFactory;
	}
 
	@Bean
	public HibernateTransactionManager transactionManager() {
		logger.info("adding transaction manager.....");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}	

	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        logger.info("adding view resolver.......");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/Views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){ 
		logger.info("adding resource registry......");
		registry.addResourceHandler("/**")
                 .addResourceLocations("classpath:/static/");
    }

	@Bean
	public MessageSource messageSource(){
		logger.info("adding Message Resource file........");
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource(); 
		source.setBasename("classpath:properties/ApplicationResources");
		source.setUseCodeAsDefaultMessage(false);
		source.setCacheSeconds(10);
		return source;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ShoppingCartAppApplication.class);
		app.setLogStartupInfo(false);
		app.run(args);
	}
	
	
}
