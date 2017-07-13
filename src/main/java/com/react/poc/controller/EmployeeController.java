package com.react.poc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.react.poc.model.Employee;
import com.react.poc.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeController {


	@Autowired
	private EmployeeService empService;

	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		empService.save(employee);
		System.out.println("Added:: " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}


	@RequestMapping(value="/update",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		Employee existingEmp = empService.getById(employee.getId());
		if (existingEmp == null) {
			System.out.println("Employee with id " + employee.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.save(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			System.out.println("Employee with id " + id + " does not exists");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Found Employee:: " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}


	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = empService.getAll();
		if (employees.isEmpty()) {
			System.out.println("Employees does not exists");
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		System.out.println("Found " + employees.size() + " Employees");
		System.out.println(employees);
		System.out.println(Arrays.toString(employees.toArray()));
		ResponseEntity<List<Employee>> temp = new ResponseEntity<>(HttpStatus.OK);
		
 		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			System.out.println("Employee with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.delete(id);
			System.out.println("Employee with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}