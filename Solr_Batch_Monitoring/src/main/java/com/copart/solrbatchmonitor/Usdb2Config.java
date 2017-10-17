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
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.us.db2.repositories", entityManagerFactoryRef = "USDb2EntityManager", transactionManagerRef = "usdb2TransactionManager")
@EnableTransactionManagement
public class Usdb2Config {
		
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.usdb2")
    public DataSource usDb2DataSource()
    {
        return DataSourceBuilder.create().build();
    }
	
	@Bean(name = "USDb2EntityManager")
    public LocalContainerEntityManagerFactoryBean UsDb2EntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
        return builder.dataSource(usDb2DataSource()).packages(
            "com.copart.solrbatchmonitor.us.db2.entities").build();
    }
	@Bean(name = "usdb2TransactionManager")
	public PlatformTransactionManager transactionManager5(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(UsDb2EntityManagerFactory(builder).getObject());
		tm.setDataSource(usDb2DataSource());
		return tm;
	}
}
