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
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.schedulerdb.repositories", entityManagerFactoryRef = "SchedulerdbEntityManager", transactionManagerRef = "schedulerdbtransactionManager")
@EnableTransactionManagement
public class SchedulerdbConfig {
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.schedulerdb")
	public DataSource schedulerdbDataSource()
    {
        return DataSourceBuilder.create().build();
    }
	@Bean(name = "SchedulerdbEntityManager")
    public LocalContainerEntityManagerFactoryBean schedulerdbEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
        return builder.dataSource(schedulerdbDataSource()).packages(
            "com.copart.solrbatchmonitor.schedulerdb.entities").persistenceUnit("guthite").build();
    }
	@Bean(name = "schedulerdbtransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(schedulerdbEntityManagerFactory(builder).getObject());
		tm.setDataSource(schedulerdbDataSource());
		return tm;
	}
}
