package com.example.demo.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;
import com.example.demo.model.EmployeeRequest;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AddressService {
	
	
	@Autowired
	private AddressCriteriaApiDemo criteriaApiDemo;
	
	@Autowired
	private AddressRepo addressRepo;
	
	
	public ResponseEntity get(String city, String state, int age, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber) {
		try {
			
			if(itemPerPage>100) {
				return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
			}
		
//			List<Address> list= criteriaApiDemo.getEmployeeAddressDataBySubQuery();
			
//			List<Address> list= criteriaApiDemo.getEmployeeAddressDataByRef();

			
//			List<Employee> list= criteriaApiDemo.getEmployeeDataOnCondition(age, salary, isRetired, joiningDate, search, searchBy, sortBy);

			
			long count =criteriaApiDemo.getCount(city, state, age, search, searchBy, sort, sortBy, itemPerPage, pageNumber);
			
			System.out.println("count==="+count);
			
			List<Address> list=new ArrayList();
             
			if(count>0) {
			  list =criteriaApiDemo.getData(city, state, age, search, searchBy, sort, sortBy, itemPerPage, pageNumber);
			}
         
		return new ResponseEntity(list, HttpStatus.OK);
        
		}catch(Exception e) {
			e.printStackTrace();
			
			 return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	
	//@Transactional
	public ResponseEntity getAddressList(long empId) {
		
		List<Address> address =addressRepo.findByEmployee_Id(empId);
		return new ResponseEntity(address, HttpStatus.OK);
	}
	
	
	
}
