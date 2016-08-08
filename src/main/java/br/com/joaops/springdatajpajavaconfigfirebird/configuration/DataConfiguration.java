/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.springdatajpajavaconfigfirebird.configuration;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author João Paulo
 */
@Configuration
@EnableJpaRepositories("br.com.joaops.springdatajpajavaconfigfirebird.repository")
@EnableTransactionManagement
@ComponentScan("br.com.joaops.springdatajpajavaconfigfirebird")
public class DataConfiguration {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
        dataSource.setUrl("jdbc:firebirdsql://localhost:3050/C:/BancoDados/BookStore.fdb");
        dataSource.setUsername("SYSDBA");
        dataSource.setPassword("masterkey");
        return dataSource;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.FirebirdDialect");
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("br.com.joaops.springdatajpajavaconfigfirebird.model");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();
        
        return factory.getObject();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
    
}