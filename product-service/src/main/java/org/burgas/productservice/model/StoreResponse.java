package org.burgas.productservice.model;

import lombok.Builder;

@Builder
public record StoreResponse(
        Long id,
        String name,
        String address
) {
}
