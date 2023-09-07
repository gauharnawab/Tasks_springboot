package com.example.demo.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

public @Data class ResponseEntityObject<T> {
	private Boolean status;
	private String message;
	private T object;
	private Long totalItems;
	private HttpStatus httpStatus;
	private int httpCode;
	public ResponseEntityObject() {
	}
	public ResponseEntityObject(HttpStatus httpStatus, int httpCode, boolean status, String message, T obj,Long totalItems) {
		super();
		this.status = status;
		this.message = message;
		this.object = obj;
		this.httpStatus = httpStatus;
		this.httpCode = httpCode;
		this.totalItems = totalItems;
	}
	
	public ResponseEntityObject(HttpStatus httpStatus, int httpCode, boolean status, String message,T list) {
		super();
		this.status = status;
		this.message = message;
		this.object = list;
		this.httpStatus = httpStatus;
		this.httpCode = httpCode;
	}
	
	public ResponseEntityObject(boolean status, String message, T obj,Long totalItems) {
		super();
		this.status = status;
		this.message = message;
		this.object = obj;
		this.totalItems = totalItems;
	}
	
	public ResponseEntityObject(boolean status, String message, T obj) {
		super();
		this.status = status;
		this.message = message;
		this.object = obj;
	}
	
	public ResponseEntityObject(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
}
