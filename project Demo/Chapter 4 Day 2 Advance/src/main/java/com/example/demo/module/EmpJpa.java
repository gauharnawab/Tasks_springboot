package com.example.demo.module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;

import jakarta.transaction.Transactional;

public interface EmpJpa extends JpaRepository<Employee, Long> {
	

	@Transactional
	@Modifying
	@Query(value = "insert into Employee(name, salary, age, is_retired, have_car) values (:name, :salary, :age, :isRetired, :haveCar)",nativeQuery = true)
	void insertEmployeeByNamedParam(@Param("name") String name, @Param("salary") double salary, @Param("age") int age, @Param("isRetired") int isRetired, @Param("haveCar") int haveCar);

	
	@Transactional
	@Modifying
	@Query(value = "insert into Employee(name, salary, age, is_retired, have_car) values (:?1, :?2, :?3, :?4, :?5)",nativeQuery = true)
	void insertEmployeeByIndexedParam(String name, double salary,int age, int isRetired, int haveCar);

	@Transactional
	@Modifying
	@Query(value = "update Employee e set e.age = ?1 where e.name = ?2", nativeQuery = true)
	int update(Integer age, String name);
	
	
	
	
}
