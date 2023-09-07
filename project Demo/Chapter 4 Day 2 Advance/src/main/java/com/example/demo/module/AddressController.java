package com.example.demo.module;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/criteria-address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity get(
			@RequestParam(name = "age", defaultValue = "0") int age,
			@RequestParam(name = "itemPerPage", defaultValue = "1") int itemPerPage,
			@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "city", defaultValue = "") String city,
			//@RequestParam(name = "date") Date date,
			@RequestParam(name = "state", defaultValue = "") String state,
			@RequestParam(name = "searchBy", defaultValue = "") String searchBy,
			 @RequestParam(name = "sort", defaultValue = "") String sort,
			 @RequestParam(name = "sortBy", defaultValue = "") String sortBy

			) {
		
		
		
		return service.get(city, state, age, search, searchBy, sort, sortBy, itemPerPage, pageNumber);
	}

	
	
}
