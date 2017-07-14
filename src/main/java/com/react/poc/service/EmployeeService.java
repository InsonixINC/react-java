package com.react.poc.service;

import java.util.List;

import com.react.poc.model.Employee;

public interface EmployeeService extends CRUDService<Employee> {

	public List<Employee> findAllByDesc();
}