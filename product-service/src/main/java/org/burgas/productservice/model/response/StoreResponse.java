package org.burgas.productservice.model.response;

import lombok.Builder;

@Builder
public record StoreResponse(
        Long id,
        String name,
        String address
) {
}
