package com.mbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.mbo.config", "com.mbo.sender"})
public class JmsHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsHelloWorldApplication.class, args);
	}

}
