package org.burgas.purchaseservice.feign;

import org.burgas.purchaseservice.model.response.PurchaseEmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "employee-service",
        url = "http://localhost:8765/rest-employees"
)
public interface EmployeeClient {

    @GetMapping("/{purchase-id}")
    ResponseEntity<PurchaseEmployeeResponse> getEmployeeByPurchaseId(
            @PathVariable(name = "purchase-id") Long purchaseId
    );
}
