package com.btit95.sample.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.btit95.sample.service.EmployeeRomoteCallService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ConsumerController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	// @Autowired
	// private LoadBalancerClient loadBalancerClient;
	@Autowired
	private EmployeeRomoteCallService employeeRomoteCallService;

	// @GetMapping("/get-employee")
	// public String getEmployee() throws RestClientException, IOException {
	// String baseUrl = "http://localhost:8080/employee";
	// ResponseEntity<String> response = restTemplate.exchange(baseUrl,
	// HttpMethod.GET, getHeaders(), String.class);
	// return response.getBody();
	// }

	@GetMapping("/get-employee")
	public String getEmployee() throws RestClientException, IOException {
		// Using with discovery eureka
		// List<ServiceInstance> instances =
		// discoveryClient.getInstances("employee-producer");
		// ServiceInstance serviceInstance = instances.get(0);

		// Using with load balancing robbin
		// ServiceInstance serviceInstance =
		// loadBalancerClient.choose("employee-producer");

		// Get producer's base URL
		// String baseUrl = serviceInstance.getUri().toString() + "/employee";
		// log.debug("Base url: {}", baseUrl);

		// ResponseEntity<String> response = restTemplate.exchange(baseUrl,
		// HttpMethod.GET, getHeaders(), String.class);
		// return response.getBody();

		// Using feign
		return new Gson().toJson(employeeRomoteCallService.getEmployee());

	}

	@GetMapping("/get-employee/{id}")
	public String getEmployee(@PathVariable("id") int id) throws RestClientException, IOException {
		// Using with load balancing robbin
		// ServiceInstance serviceInstance =
		// loadBalancerClient.choose("employee-producer");

		// Get producer's base URL
		// String baseUrl = serviceInstance.getUri().toString() + "/employee/" +
		// id;
		// log.debug("Base url: {}", baseUrl);
		//
		// ResponseEntity<String> response = restTemplate.exchange(baseUrl,
		// HttpMethod.GET, getHeaders(), String.class);
		// return response.getBody();

		// Using feign
		return new Gson().toJson(employeeRomoteCallService.getById(id));
	}

	private HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	@GetMapping("/get-employees")
	public String getAll() throws RestClientException, IOException {
		// Using with discovery eureka
		List<ServiceInstance> instances = discoveryClient.getInstances("zuul-api-gateway");
		ServiceInstance serviceInstance = instances.get(0);

		// Get producer's base URL from zuul-api-gateway
		String baseUrl = serviceInstance.getUri().toString() + "/producer/employees";
		log.debug("Base url: {}", baseUrl);

		ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		return response.getBody();
	}
}
