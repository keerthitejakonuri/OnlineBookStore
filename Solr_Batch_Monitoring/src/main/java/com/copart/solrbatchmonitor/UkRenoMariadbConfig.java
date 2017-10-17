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
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.uk.renomariadb.repositories", entityManagerFactoryRef = "UkRenoMariadbEntityManager", transactionManagerRef = "ukrenotransactionManager")
@EnableTransactionManagement
public class UkRenoMariadbConfig {
		
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.ukrenomariadb")
	public DataSource ukRenoMariadbDataSource()
    {
        return DataSourceBuilder.create().build();
    }
	@Bean(name = "UkRenoMariadbEntityManager")
    public LocalContainerEntityManagerFactoryBean UkRenoMariadbEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
       /* Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.show-sql", "true");*/
        return builder.dataSource(ukRenoMariadbDataSource()).packages(
            "com.copart.solrbatchmonitor.uk.renomariadb.entities").persistenceUnit("gbrprddb").build();
    }
	@Bean(name = "ukrenotransactionManager")
	public PlatformTransactionManager transactionManager3(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(UkRenoMariadbEntityManagerFactory(builder).getObject());
		tm.setDataSource(ukRenoMariadbDataSource());
		return tm;
	}
	
}
