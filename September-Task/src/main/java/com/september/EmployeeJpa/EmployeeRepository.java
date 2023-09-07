package com.september.EmployeeJpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.september.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
}
