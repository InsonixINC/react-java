package com.react.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react.poc.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}