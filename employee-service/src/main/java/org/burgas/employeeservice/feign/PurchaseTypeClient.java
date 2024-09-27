package org.burgas.employeeservice.feign;

import org.burgas.employeeservice.model.response.PurchaseTypeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "rest-purchase-types",
        url = "http://localhost:8765/rest-purchase-types"
)
public interface PurchaseTypeClient {

    @GetMapping
    ResponseEntity<List<PurchaseTypeResponse>> getAllPurchaseTypes();
}
