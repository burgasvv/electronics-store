package org.burgas.purchaseservice.model;

import lombok.Builder;

@Builder
public record PurchaseResponse(
        Long id,
        PurchaseProductResponse purchaseProductResponse,
        PurchaseEmployeeResponse purchaseEmployeeResponse,
        PurchaseStoreResponse purchaseStoreResponse,
        PurchaseTypeResponse purchaseTypeResponse,
        String dateTime
) {
}
