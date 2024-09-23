package org.burgas.productservice.feign;

import org.burgas.productservice.model.StoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "store-service",
        url = "http://localhost:8765/rest-stores"
)
public interface StoreClient {

    @GetMapping("/product/{product-id}")
    ResponseEntity<List<StoreResponse>> getStoresByProductId(
            @PathVariable(name = "product-id") Long productId
    );
}
