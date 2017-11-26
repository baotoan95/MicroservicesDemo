package com.btit95.sample.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.btit95.sample.entities.Employee;

@FeignClient(name = "employee-producer")
public interface EmployeeRomoteCallService {
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee getEmployee();
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getById(@PathVariable("id") int id);
}
