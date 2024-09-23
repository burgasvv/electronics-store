package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/employees";
    }

    @GetMapping("/{employee-id}")
    public String getEmployee(
            @PathVariable(name = "employee-id") Long employeeId, Model model
    ) {
        model.addAttribute("employee", employeeService.findById(employeeId));
        return "employees/employee";
    }

    @GetMapping("/best-employees")
    public String getBestEmployees(Model model) {
        model.addAttribute("bestEmployees", employeeService.findBestEmployees());
        return "employees/bestEmployees";
    }

    @GetMapping("/best-junior-consultant")
    public String getBestJuniorConsultant(Model model) {
        model.addAttribute("bestJuniorConsultants", employeeService.findBestJuniorConsultant());
        return "employees/bestJuniorConsultant";
    }
}
