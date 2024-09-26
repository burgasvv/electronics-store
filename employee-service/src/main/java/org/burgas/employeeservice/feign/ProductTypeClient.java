package org.burgas.employeeservice.feign;

import org.burgas.employeeservice.model.response.ProductTypeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8765/rest-product-types"
)
public interface ProductTypeClient {

    @GetMapping("/all-product-types")
    ResponseEntity<List<ProductTypeResponse>> getAllProductTypes();

    @GetMapping("/{employee-id}")
    ResponseEntity<List<ProductTypeResponse>> getEmployeeProductTypes(
            @PathVariable(name = "employee-id") Long employeeId
    );
}
