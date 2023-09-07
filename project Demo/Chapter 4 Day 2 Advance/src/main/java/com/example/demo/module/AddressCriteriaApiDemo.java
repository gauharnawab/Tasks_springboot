package com.example.demo.module;

import java.util.Date;
import java.util.List;

import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;

public interface AddressCriteriaApiDemo {
	
	
	long getCount(String city, String state, int age, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber);

	
	List<Address> getData(String city, String state, int age, String search, String searchBy, String sort, String sortBy, int itemPerPage, int pageNumber);

	

}
