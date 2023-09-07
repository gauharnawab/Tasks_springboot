package com.example.demo.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
public @Data class Employee {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(strategy = "native", name = "native")
		@Column(name = "employee_id")
	   private int id;
	   private String name;
	   private double salary;
	   private int isRetired;
	   
	   @Column(name = "age")
	   private int age;
	   
//	   @ManyToOne
//	   @JoinColumn(name = "status_id")
//	   private Status status;
	   
	   public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status; //"PENDING", "APPROVED"
	   
	   @Column(name = "joining_date")
	   private Date joiningDate;
	   
	   public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHaveCar() {
		return haveCar;
	}

	public void setHaveCar(int haveCar) {
		this.haveCar = haveCar;
	}

	private  String gender;
       
	   private int haveCar;
}
