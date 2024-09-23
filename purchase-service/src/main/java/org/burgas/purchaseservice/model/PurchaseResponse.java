package org.burgas.purchaseservice.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PurchaseResponse(
        Long id,
        PurchaseProductResponse purchaseProductResponse,
        PurchaseEmployeeResponse purchaseEmployeeResponse,
        PurchaseStoreResponse purchaseStoreResponse,
        PurchaseTypeResponse purchaseTypeResponse,
        LocalDateTime purchaseDateTime
) {
}
