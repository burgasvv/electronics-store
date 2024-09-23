package org.burgas.storeservice.model;

import lombok.Builder;

@Builder
public record PurchaseStoreResponse(
        Long id,
        String name,
        String address
) {
}
