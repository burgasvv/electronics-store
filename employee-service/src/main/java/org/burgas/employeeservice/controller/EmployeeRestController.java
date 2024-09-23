package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.model.EmployeeResponse;
import org.burgas.employeeservice.model.PurchaseEmployeeResponse;
import org.burgas.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/{purchase-id}")
    public ResponseEntity<PurchaseEmployeeResponse> getEmployeeByPurchaseId(
            @PathVariable(name = "purchase-id") Long purchaseId
    ) {
        return ResponseEntity.ok(
                employeeService.findByPurchaseId(purchaseId)
        );
    }

    @GetMapping("/store/{store-id}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByStoreId(
            @PathVariable(name = "store-id") Long storeId
    ) {
        return ResponseEntity.ok(
                employeeService.findByStoreId(storeId)
        );
    }
}
