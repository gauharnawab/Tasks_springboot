package com.example.demo.module;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;
import com.example.demo.model.CustomResponse;

import jakarta.transaction.Transactional;

public interface AddressRepo extends JpaRepository<Address, Long> {
	
	
	//sub query
	@Transactional
	@Modifying
	@Query(value="select * from address a where a.city=?1 and a.pin_code=?2 and a.employee_id in \n"
			+ " ( select employee_id from employee e where e.age=?3 and e.salary=?4)", nativeQuery = true)
	List<Address> fetchdata(String city, String pincode, int age, double salary);

	
	
	//sub query
	@Transactional
	@Modifying
	@Query(value="select distinct a.state from address a where a.city=?1 and a.pin_code=?2 and a.employee_id in ( select employee_id from employee e where e.age=?3)", nativeQuery = true)
	List<String> fetchState(String city, String pincode, int age);

	
	//sub query
	@Transactional
	@Modifying
	@Query(value="select a.state as stateName, a.pin_code as postalCode from address a where a.city=?1 and a.employee_id in ( select employee_id from employee e where e.age=?2)", nativeQuery = true)
	List<CustomResponse> fetchStateNameAndPinCode(String city,int age);
	
	
	List<Address> findByEmployee_Id(long empId);
	
}
