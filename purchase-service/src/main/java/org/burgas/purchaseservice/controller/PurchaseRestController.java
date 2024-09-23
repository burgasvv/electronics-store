package org.burgas.purchaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.model.PurchaseResponse;
import org.burgas.purchaseservice.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-purchases")
public class PurchaseRestController {

    private final PurchaseService purchaseService;

    @GetMapping("/employee/{employee-id}")
    public ResponseEntity<List<PurchaseResponse>> getPurchasesByEmployee(
            @PathVariable(name = "employee-id") Long employeeId
    ) {
        return ResponseEntity.ok(
                purchaseService.findByEmployeeId(employeeId)
        );
    }
}
