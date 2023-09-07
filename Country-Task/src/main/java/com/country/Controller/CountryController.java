package com.country.Controller;

import com.country.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/countries-list")
public List<Map<String, Object>> getCountriesAndStates() {
    List<Object[]> result = countryRepository.getCountriesAndStates();

    List<Map<String, Object>> response = new ArrayList<>();

    for (Object[] row : result) {
        Map<String, Object> countryAndState = new HashMap<>();
        countryAndState.put("countryName", row[0]); // Assuming the first column represents the country name
        countryAndState.put("stateName", row[1]);   // Assuming the second column represents the state name
        response.add(countryAndState);
    }

    return response;
}

}
