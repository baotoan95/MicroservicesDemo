package com.btit95.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.btit95.sample.entities.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {
	private List<Employee> employees;
	
	public EmployeeController() {
		this.employees = new ArrayList<>();
		this.employees.add(new Employee(1, "Bao Toan", "This is designation", 3243));
		this.employees.add(new Employee(2, "Van Long", "This is designation", 3243));
		this.employees.add(new Employee(3, "Hoang Hao", "This is designation", 3243));
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return this.employees;
	}
	
	@GetMapping("/employee")
	public Employee findOne() {
		return new Employee(123, "Bao Toan", "This is designation", 1324);
	}
	
	@GetMapping("/employee/{id}")
	@HystrixCommand(fallbackMethod = "getDataFallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5")
	})
	public Employee findById(@PathVariable("id") int id) {
		log.debug("Inside findById");
		Employee employee = this.employees.stream().filter(emp -> emp.getId() == id).findFirst().get();
		return employee;
	}
	
	public Employee getDataFallback(int id) {
		log.debug("Inside getDataFallback");
		return new Employee(0, "Anonymous", "This is fellback employee", 1);
	}
}
