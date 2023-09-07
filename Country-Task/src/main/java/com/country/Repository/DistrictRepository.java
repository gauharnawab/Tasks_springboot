package com.country.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.country.entity.District;

public interface DistrictRepository extends JpaRepository<District,Long>{
    
}
