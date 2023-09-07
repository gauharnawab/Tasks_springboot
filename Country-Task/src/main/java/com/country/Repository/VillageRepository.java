package com.country.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.country.entity.Village;

public interface VillageRepository extends JpaRepository<Village, Long> {
    @Query(value = "SELECT d.district_name AS city, v.village_name AS village " +
                   "FROM village v " +
                   "INNER JOIN district d ON v.district_id = d.id " +
                   "WHERE v.pincode IN :pincode", nativeQuery = true)
    List<Object[]> getCitiesAndVillagesByPinCodes(@Param("pincode") List<String> pincode);
}