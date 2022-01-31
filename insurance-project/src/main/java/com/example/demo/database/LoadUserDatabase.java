package com.example.demo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.car.Car;
import com.example.demo.customer.Customer;
import com.example.demo.insurance.Insurance;

@Configuration
public class LoadUserDatabase {

	  private static final Logger LOGGER = LoggerFactory.getLogger(LoadUserDatabase.class);

	  @Bean
	  CommandLineRunner initDatabase(IUserRepository repository) {
	    return args -> {
			LOGGER.info("Preloading " + repository.save(new Customer("Evangelos", "Georgiou", 22, new Insurance(new Car("honda", "civic")))));
	    	LOGGER.info("Preloading " + repository.save(new Customer("Kyriakos", "Georgiou", 23, new Insurance(new Car("honda", "civic")))));
	    };
	  }
}
