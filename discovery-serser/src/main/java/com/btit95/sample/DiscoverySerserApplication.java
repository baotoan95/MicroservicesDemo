package com.btit95.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoverySerserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoverySerserApplication.class, args);
	}
}
