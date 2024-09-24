package org.burgas.purchaseservice.model.response;

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
