package com.september.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.september.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/calculate-yearly")
    public ResponseEntity<Double> calculateYearlyExpenses() {
        double totalExpenses = employeeService.calculateYearlyExpenses();
        return ResponseEntity.ok(totalExpenses);
    }
}


