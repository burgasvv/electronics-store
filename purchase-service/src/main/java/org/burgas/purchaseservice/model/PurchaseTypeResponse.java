package org.burgas.purchaseservice.model;

import lombok.Builder;

@Builder
public record PurchaseTypeResponse(
        Long id,
        String name
) {
}
