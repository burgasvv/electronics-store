package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.feign.ProductTypeClient;
import org.burgas.employeeservice.feign.StoreClient;
import org.burgas.employeeservice.model.request.EmployeeRequest;
import org.burgas.employeeservice.model.response.EmployeeResponse;
import org.burgas.employeeservice.service.EmployeeService;
import org.burgas.employeeservice.service.PositionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final StoreClient storeClient;
    private final ProductTypeClient productTypeClient;

    @GetMapping
    public String getAllEmployees(Model model) {
        return getEmployeesPage(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getEmployeesPage(
            @PathVariable int page, Model model
    ) {
        Page<EmployeeResponse> employeePages = employeeService.findAllPages(page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, employeePages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("employees", employeePages.getContent());
        return "employees/employees";
    }

    @GetMapping("/{employee-id}")
    public String getEmployee(
            @PathVariable(name = "employee-id") Long employeeId, Model model
    ) {
        model.addAttribute("employee", employeeService.findById(employeeId));
        return "employees/employee";
    }

    @GetMapping("/store/{store-id}")
    public String getEmployeesInStore(
            @PathVariable(name = "store-id") Long storeId, Model model
    ) {
        model.addAttribute("storeId", storeId);
        return getEmployeesInStorePages(1, storeId, model);
    }

    @GetMapping("/store-employees/{store-id}/pages/{page}")
    public String getEmployeesInStorePages(
            @PathVariable int page, @PathVariable(name = "store-id") Long storeId, Model model
    ) {
        Page<EmployeeResponse> employeesPages = employeeService.findPagesByStoreId(storeId, page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, employeesPages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("employees", employeesPages.getContent());
        return "employees/employeesInStore";
    }

    @GetMapping("/position/{position-id}")
    public String getEmployeesByPosition(
            @PathVariable(name = "position-id") Long positionId, Model model
    ) {
        model.addAttribute("positionId", positionId);
        return getEmployeesByPositionPages(1, positionId, model);
    }

    @GetMapping("/position-employees/{position-id}/pages/{page}")
    public String getEmployeesByPositionPages(
            @PathVariable int page, @PathVariable(name = "position-id") Long positionId, Model model
    ) {
        Page<EmployeeResponse> employeesPages = employeeService.findPagesByPositionId(positionId, page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, employeesPages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("employees", employeesPages.getContent());
        return "employees/employeesByPosition";
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

    @PostMapping("/save-from-csv")
    public String saveDataFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        employeeService.saveFromCsvFile(file);
        return "redirect:http://localhost:8765/employees";
    }

    @GetMapping("/add-employee-page")
    public String addEmployeePage(Model model) {
        model.addAttribute("newEmployee", new EmployeeRequest());
        model.addAttribute("positions", positionService.findAll());
        model.addAttribute("stores", storeClient.getAllStores().getBody());
        model.addAttribute("productTypes", productTypeClient.getAllProductTypes().getBody());
        return "employees/addEmployee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute EmployeeRequest employeeRequest) {
        return "redirect:http://localhost:8765/employees/"
               + employeeService.addEmployee(employeeRequest).getId();
    }

    @GetMapping("/edit-employee-page/{employee-id}")
    public String editEmployeePage(
            @PathVariable(name = "employee-id") Long employeeId, Model model
    ) {
        model.addAttribute("employee", employeeService.findEmployeeRequestById(employeeId));
        model.addAttribute("positions", positionService.findAll());
        model.addAttribute("stores", storeClient.getAllStores().getBody());
        model.addAttribute("productTypes", productTypeClient.getAllProductTypes().getBody());
        return "employees/editEmployee";
    }

    @PostMapping("/edit-employee")
    public String editEmployee(@ModelAttribute EmployeeRequest employeeRequest) {
        return "redirect:http://localhost:8765/employees/"
               + employeeService.editEmployee(employeeRequest).getId();
    }

    @PostMapping("/delete-employee/{employee-id}")
    public String deleteEmployee(
            @PathVariable(name = "employee-id") Long employeeId
    ) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:http://localhost:8765/employees";
    }
}
