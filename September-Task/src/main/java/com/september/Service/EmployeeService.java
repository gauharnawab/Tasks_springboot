package com.september.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.september.EmployeeJpa.EmployeeRepository;
import com.september.Entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public double calculateYearlyExpenses() {
        List<Employee> employees = employeeRepository.findAll();
        double totalExpenses = 0;

        for (Employee employee : employees) {
            double salary = employee.getSalary();
            double performanceScore = employee.getPerformanceScore();
            double quarterlyBonus = 0.0;
            double monthlyIncentive = 0.0;

            if (performanceScore >= 0.8) {
                quarterlyBonus = 0.2 * salary;
                if (performanceScore >= 0.9) {
                    monthlyIncentive = 0.05 * salary * 12;
                }
            }

            totalExpenses += (salary * 12) + quarterlyBonus + monthlyIncentive;
        }

        return totalExpenses;
    }
}
