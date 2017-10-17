package com.copart.solrbatchmonitor;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.copart.solrbatchmonitor.us.renomariadb.repositories", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "usrenotransactionManager")
@EnableTransactionManagement
public class UsRenoMariadbConfig {
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.usrenomariadb")
	@Primary
    public DataSource usRenoMariadbDataSource()
    {
        return DataSourceBuilder.create().build();
    }
	
	@Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean UsRenoMariadbEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
       /* Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.show-sql", "true");*/
        return builder.dataSource(usRenoMariadbDataSource()).packages(
            "com.copart.solrbatchmonitor.us.renomariadb.entities").persistenceUnit("misprddb").build();
    }
	
	@Bean(name = "usrenotransactionManager")
	 @Primary
	public PlatformTransactionManager transactionManager1(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(UsRenoMariadbEntityManagerFactory(builder).getObject());
		tm.setDataSource(usRenoMariadbDataSource());
		return tm;
	}
}
