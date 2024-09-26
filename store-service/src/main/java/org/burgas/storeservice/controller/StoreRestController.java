package org.burgas.storeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.model.response.PurchaseStoreResponse;
import org.burgas.storeservice.model.response.StoreResponse;
import org.burgas.storeservice.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-stores")
public class StoreRestController {

    private final StoreService storeService;

    @GetMapping("/{store-id}")
    public ResponseEntity<StoreResponse> getStoreById(
            @PathVariable(name = "store-id") Long storeId
    ) {
        return ResponseEntity.ok(storeService.findById(storeId));
    }

    @GetMapping("/product/{product-id}")
    public ResponseEntity<List<StoreResponse>> getStoresByProductId(
            @PathVariable(name = "product-id") Long productId
    ) {
        return ResponseEntity.ok(storeService.findStoresByProductId(productId));
    }

    @GetMapping("/purchase/{purchase-id}")
    public ResponseEntity<PurchaseStoreResponse> getStoreByPurchaseId(
            @PathVariable(name = "purchase-id") Long purchaseId
    ) {
        return ResponseEntity.ok(
                storeService.findStoreByPurchaseId(purchaseId)
        );
    }

    @GetMapping("/new-purchase/{product-id}")
    public ResponseEntity<List<PurchaseStoreResponse>> getStoresByProductInStock(
            @PathVariable(name = "product-id") Long productId
    ) {
        return ResponseEntity.ok(
                storeService.findStoresByProductIdInStock(productId)
        );
    }

    @GetMapping("/all-stores")
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        return ResponseEntity.ok(storeService.findAll());
    }
}
