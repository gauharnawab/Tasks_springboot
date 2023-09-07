package com.country.Controller;




import com.country.Repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/villages")
public class VillageController {

    private final VillageRepository villageRepository;

    @Autowired
    public VillageController(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    @GetMapping("/city-village")
    public List<Object[]> getCitiesAndVillagesByPinCodes(@RequestParam("pincode") List<String> pincode) {
        return villageRepository.getCitiesAndVillagesByPinCodes(pincode);
    }
}



