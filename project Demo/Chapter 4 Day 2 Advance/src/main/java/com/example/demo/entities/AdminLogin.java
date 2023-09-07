package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "admin")
public @Data class AdminLogin {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(strategy = "native", name = "native")
		@Column(name = "admin_login_id")
		 private long id;
		
		
	   private String username;
	   private String password;

}
