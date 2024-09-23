package org.burgas.purchaseservice.feign;

import org.burgas.purchaseservice.model.PurchaseStoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "store-service",
        url = "http://localhost:8765/rest-stores"
)
public interface StoreClient {

    @GetMapping("/purchase/{purchase-id}")
    ResponseEntity<PurchaseStoreResponse> getStoreByPurchaseId(
            @PathVariable(name = "purchase-id") Long purchaseId
    );
}
