package com.btit95.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipkinTracingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinTracingServerApplication.class, args);
	}
}
