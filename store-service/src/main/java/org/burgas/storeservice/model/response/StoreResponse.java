package org.burgas.storeservice.model.response;

import lombok.Builder;

@Builder
public record StoreResponse(
        Long id,
        String name,
        String address
) {
}
