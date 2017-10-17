package com.copart.solrbatchmonitor;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.us.colomariadb.repositories", entityManagerFactoryRef = "UsColoMariadbEntityManager", transactionManagerRef = "uscolotransactionManager")
@EnableTransactionManagement
public class UsColoMariadbConfig {
	
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.uscolomariadb")
	public DataSource usColoMariadbDataSource()
    {
        return DataSourceBuilder.create().build();
    }
	@Bean(name = "UsColoMariadbEntityManager")
    public LocalContainerEntityManagerFactoryBean UsColoMariadbEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
		
        return builder.dataSource(usColoMariadbDataSource()).packages("com.copart.solrbatchmonitor.us.colomariadb.entities").persistenceUnit("misprddb").build();
    	
		
    }
	
	@Bean(name = "uscolotransactionManager")
	public PlatformTransactionManager transactionManager2(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(UsColoMariadbEntityManagerFactory(builder).getObject());
		tm.setDataSource(usColoMariadbDataSource());
		return tm;
	}
	
}
