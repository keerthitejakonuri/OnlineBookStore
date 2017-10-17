package com.copart.solrbatchmonitor;

//import java.util.Timer;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.security.auth.message.config.AuthConfigFactory;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;



//@EnableAutoConfiguration(exclude={JpaRepositoriesAutoConfiguration.class})
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@ComponentScan("com.copart.solrbatchmonitor")

public class Application  {
		
	public static void main(String[] args) {
		if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
		SpringApplication.run(Application.class, args);
		
	}
}


/*This is the Main class of the application */