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
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.uk.colomariadb.repositories", entityManagerFactoryRef = "UkColoMariadbEntityManager", transactionManagerRef = "ukcolotransactionManager")
@EnableTransactionManagement
public class UkColoMariadbConfig {
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.ukcolomariadb")
	public DataSource ukColoMariadbDataSource()
    {
        return DataSourceBuilder.create().build();
    }
	@Bean(name = "UkColoMariadbEntityManager")
    public LocalContainerEntityManagerFactoryBean UkColoMariadbEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
        return builder.dataSource(ukColoMariadbDataSource()).packages(
            "com.copart.solrbatchmonitor.uk.colomariadb.entities").persistenceUnit("gbrprddb").build();
    }
	@Bean(name = "ukcolotransactionManager")
	public PlatformTransactionManager transactionManager4(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(UkColoMariadbEntityManagerFactory(builder).getObject());
		tm.setDataSource(ukColoMariadbDataSource());
		return tm;
	}
}
