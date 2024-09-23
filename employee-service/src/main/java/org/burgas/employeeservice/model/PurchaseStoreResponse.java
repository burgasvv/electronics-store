package org.burgas.employeeservice.model;

import lombok.Builder;

@Builder
public record PurchaseStoreResponse(
        Long id,
        String name,
        String address
) {
}
