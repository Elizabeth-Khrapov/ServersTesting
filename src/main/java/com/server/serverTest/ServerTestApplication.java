package com.server.serverTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ServerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTestApplication.class, args);
	}

}
