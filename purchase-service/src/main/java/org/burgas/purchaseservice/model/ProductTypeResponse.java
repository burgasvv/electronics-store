package org.burgas.purchaseservice.model;

import lombok.Builder;

@Builder
public record ProductTypeResponse(
        Long id,
        String name
) {
}
