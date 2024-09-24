package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.response.PurchaseProductResponse;
import org.burgas.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{purchase-id}")
    public ResponseEntity<PurchaseProductResponse> getProductByPurchaseId(
            @PathVariable(name = "purchase-id") Long purchaseId
    ) {
        return ResponseEntity.ok(
                productService.findProductByPurchaseId(purchaseId)
        );
    }

    @GetMapping("/{employee-id}/{purchase-id}")
    public ResponseEntity<PurchaseProductResponse> getProductByEmployeeAndPurchase(
            @PathVariable(name = "employee-id") Long employeeId,
            @PathVariable(name = "purchase-id") Long purchaseId
    ) {
        return ResponseEntity.ok(
                productService.findProductByEmployeeIdAndPurchaseId(employeeId, purchaseId)
        );
    }
}
