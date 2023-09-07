package com.example.demo.module;

import java.util.Date;
import java.util.List;

import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;

public interface EmpRepo {
	
	
	long getCount(int age, String status, String search, String searchBy, String sortOrder, String sortBy, int itemPerPage, int pageNumber);

	
	List<Employee> getData(int age,String status, String search, String searchBy, String sortOrder, String sortBy, int itemPerPage, int pageNumber);

	

}
