package org.burgas.employeeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {

    private Long id;
    private PurchaseProductResponse purchaseProductResponse;
    private PurchaseEmployeeResponse purchaseEmployeeResponse;
    private PurchaseStoreResponse purchaseStoreResponse;
    private PurchaseTypeResponse purchaseTypeResponse;
    private String dateTime;
}
