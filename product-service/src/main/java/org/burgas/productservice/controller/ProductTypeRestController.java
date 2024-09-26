package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.response.ProductTypeResponse;
import org.burgas.productservice.service.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-product-types")
public class ProductTypeRestController {

    private final ProductTypeService productTypeService;

    @GetMapping("/all-product-types")
    public ResponseEntity<List<ProductTypeResponse>> getAllProductTypes() {
        return ResponseEntity.ok(productTypeService.findAll());
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<List<ProductTypeResponse>> getEmployeeProductTypes(
            @PathVariable(name = "employee-id") Long employeeId
    ) {
        return ResponseEntity.ok(productTypeService.findTypesByEmployeeId(employeeId));
    }
}
