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
import com.example.demo.model.LoginRequest;
import com.example.demo.model.ResponseEntityObject;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EmployeeService {
	
	
	@Autowired
	private EmpRepo empRepo;
	
	
	
	@Autowired
	private AdminLoginJpaRepo adminLoginRepo;
	
	
	@Autowired
	private EmpJpa empJpa;
	
	
	public ResponseEntity login(LoginRequest request) {
		try {
			
			if(adminLoginRepo.existsByUsernameAndPassword(request.getUsername(), request.getPassword())) {
				    return new ResponseEntity(new ResponseEntityObject(true, "SUCCESS LOGIN"), HttpStatus.OK);
			}else {
			       return new ResponseEntity(new ResponseEntityObject(false, "USERNAME AND PASSWORD WRONG"), HttpStatus.OK);

			}
        
        
		}catch(Exception e) {
			e.printStackTrace();
			
			 return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	public ResponseEntity get(int age, String status, String search, String searchBy, String sortOrder, String sortBy, int itemPerPage, int pageNumber) {
		try {
			

			List<Employee> list= empRepo.getData(age,status, search, searchBy, sortOrder,sortBy, itemPerPage, pageNumber);

			
			        long count =empRepo.getCount(age,status, search, searchBy, sortOrder, sortBy, itemPerPage, pageNumber);
			
			        System.out.println("count======="+count);
         
		   return new ResponseEntity(list, HttpStatus.OK);
        
		}catch(Exception e) {
			e.printStackTrace();
			
			 return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	public ResponseEntity get(long id ) {
		try {
			
			Employee emp= empJpa.findById(id).orElse(null);
         
		   return new ResponseEntity(emp, HttpStatus.OK);
        
		}catch(Exception e) {
			e.printStackTrace();
			
			 return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Transactional
	public ResponseEntity save(EmployeeRequest request) {
		
		
//		empJpa.insertEmployeeByNamedParam(request.getName(), request.getSalary(), request.getAge(), request.getIsRetired(),0);

		empJpa.insertEmployeeByIndexedParam(request.getName(), request.getSalary(), request.getAge(), request.getIsRetired(),0);

		
		return null;
	}
	
	
	
	@Transactional
	public ResponseEntity update(EmployeeRequest request) {
		
		
		empJpa.update(request.getAge(), request.getName());

		
		return null;
	}
	
	
}
