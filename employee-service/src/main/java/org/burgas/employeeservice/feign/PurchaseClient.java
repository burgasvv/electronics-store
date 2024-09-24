package org.burgas.employeeservice.feign;

import org.burgas.employeeservice.model.response.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "purchase-service",
        url = "http://localhost:8765/rest-purchases"
)
public interface PurchaseClient {

    @GetMapping("/employee/{employee-id}")
    ResponseEntity<List<PurchaseResponse>> getPurchasesByEmployee(
            @PathVariable(name = "employee-id") Long employeeId
    );
}
