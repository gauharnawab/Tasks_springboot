package com.email.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
