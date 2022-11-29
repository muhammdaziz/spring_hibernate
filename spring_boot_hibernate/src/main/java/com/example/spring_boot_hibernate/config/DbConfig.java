package com.example.spring_boot_hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource(value = {"classpath:application.properties"})
@Configuration
public class DbConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClass;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory factory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(factory);

        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setHibernateProperties(properties());
        sessionFactory.setPackagesToScan("com.example.spring_boot_hibernate.model");

        return sessionFactory;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);

        dataSource.setDriverClassName(driverClass);

        return dataSource;
    }

    private Properties properties(){
        Properties properties = new Properties();

        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return properties;
    }


}
