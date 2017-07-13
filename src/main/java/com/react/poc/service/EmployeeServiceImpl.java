package com.react.poc.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.poc.model.Employee;
import com.react.poc.repo.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee entity) {
		return employeeRepository.save(entity);
	}

	@Override
	public Employee getById(Serializable id) {
		return employeeRepository.findOne((Long) id);
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		employeeRepository.delete((Long) id);
	}

}
