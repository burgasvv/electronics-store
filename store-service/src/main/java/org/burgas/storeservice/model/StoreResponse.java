package org.burgas.storeservice.model;

import lombok.Builder;

@Builder
public record StoreResponse(
        Long id,
        String name,
        String address
) {
}
