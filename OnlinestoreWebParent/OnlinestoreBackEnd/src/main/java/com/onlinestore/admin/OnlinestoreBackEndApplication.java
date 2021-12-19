package com.onlinestore.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.onlinestore.common.entity"})
public class OnlinestoreBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinestoreBackEndApplication.class, args);
	}

}
