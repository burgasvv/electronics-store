package org.burgas.purchaseservice.feign;

import org.burgas.purchaseservice.model.PurchaseProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8765/rest-products"
)
public interface ProductClient {

    @GetMapping("/{purchase-id}")
    ResponseEntity<PurchaseProductResponse> getProductByPurchaseId(
            @PathVariable(name = "purchase-id") Long purchaseId
    );
}
