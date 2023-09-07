package com.example.demo.module;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeRequest;
import com.example.demo.model.LoginRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "/emp-management")
public class EmpController {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private AddressService addressService;
	
	
	
	@RequestMapping(path ="/login", method = RequestMethod.POST)
	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		
		return service.login(loginRequest);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path ="/fetch")
	public ResponseEntity get(
			@RequestParam(name = "age", defaultValue = "0") int age,
			
			@RequestParam(name = "status", defaultValue = "") String status,

			
			@RequestParam(name = "itemsPerPage", defaultValue = "1") int itemPerPage,
			@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
			
			@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "searchBy", defaultValue = "") String searchBy,
			
			 @RequestParam(name = "sortOrder", defaultValue = "") String sort,
			 @RequestParam(name = "sortBy", defaultValue = "") String sortBy

			) {
		
		
		
		return service.get(age, status, search, searchBy, sort, sortBy, itemPerPage, pageNumber);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path ="/single/{id}")
	public ResponseEntity getEmp(
			@PathVariable(name = "id") Long id
			) {
		
		return service.get(id);
	}


	
	
	
	@RequestMapping(method = RequestMethod.POST, path ="/save")
	public ResponseEntity save(@RequestBody EmployeeRequest req) {
       return service.save(req);
    }
	
	
	
	@RequestMapping(method = RequestMethod.PUT, path ="/update")
	public ResponseEntity update(@RequestBody EmployeeRequest req) {
       return service.update(req);
    }
	
	
	
//	@RequestMapping(method = RequestMethod.GET, path ="/get")
//	public ResponseEntity getData() {
//       return addressService.getData();
//    }
	
	@RequestMapping(method = RequestMethod.GET, path ="/address-list/{empId}")
	public ResponseEntity getAddressList(
			@PathVariable(name = "empId") Long empId
			) {
       return addressService.getAddressList(empId);
    }
}
