package com.country.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.country.entity.Country;

public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query(value = "SELECT c.name AS country_name, s.state_name " +
                   "FROM country c " +
                   "INNER JOIN state s ON c.id = s.country_id", nativeQuery = true)
    List<Object[]> getCountriesAndStates();
}
