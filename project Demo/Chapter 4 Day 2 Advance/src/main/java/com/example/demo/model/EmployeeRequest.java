package com.example.demo.model;

import lombok.Data;

public @Data class EmployeeRequest {
	
	private String name;
	private int age;
	private double salary;
	private int isRetired;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getIsRetired() {
		return isRetired;
	}
	public void setIsRetired(int isRetired) {
		this.isRetired = isRetired;
	}

}
