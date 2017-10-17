package com.copart.solrbatchmonitor;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.uk.db2.repositories", entityManagerFactoryRef = "UkDb2EntityManager", transactionManagerRef = "ukdb2TransactionManager")
@EnableTransactionManagement
public class Ukdb2Config {
		
	
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.ukdb2")
    public DataSource ukDb2DataSource()
    {
        return DataSourceBuilder.create().build();
    }
	
	@Bean(name = "UkDb2EntityManager")
    public LocalContainerEntityManagerFactoryBean UkDb2EntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
       /* Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.show-sql", "true");*/
        return builder.dataSource(ukDb2DataSource()).packages(
            "com.copart.solrbatchmonitor.uk.db2.entities").build();
    }
	@Bean(name = "ukdb2TransactionManager")
	public PlatformTransactionManager transactionManager6(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(UkDb2EntityManagerFactory(builder).getObject());
		tm.setDataSource(ukDb2DataSource());
		return tm;
	}
}
