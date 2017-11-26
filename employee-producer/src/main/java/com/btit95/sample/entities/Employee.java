package com.btit95.sample.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Employee {
	private int id;
	private String name;
	private String designation;
	private double salary;
}
