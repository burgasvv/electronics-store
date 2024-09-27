package org.burgas.purchaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.model.response.PurchaseTypeResponse;
import org.burgas.purchaseservice.service.PurchaseTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-purchase-types")
public class PurchaseTypeRestController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping
    public ResponseEntity<List<PurchaseTypeResponse>> getAllPurchaseTypes() {
        return ResponseEntity.ok(purchaseTypeService.findAll());
    }
}
