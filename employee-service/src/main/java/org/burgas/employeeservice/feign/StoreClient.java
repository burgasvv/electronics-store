package org.burgas.employeeservice.feign;

import org.burgas.employeeservice.model.response.StoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "store-service",
        url = "http://localhost:8765/rest-stores"
)
public interface StoreClient {

    @GetMapping("/{store-id}")
    ResponseEntity<StoreResponse> getStoreById(
            @PathVariable(name = "store-id") Long storeId
    );
}
