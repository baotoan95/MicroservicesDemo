package com.btit95.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.btit95.sample.filter.ErrorFilter;
import com.btit95.sample.filter.PostFilter;
import com.btit95.sample.filter.PreFilter;
import com.btit95.sample.filter.RouteFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
}
