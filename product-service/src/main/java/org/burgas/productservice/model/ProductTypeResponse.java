package org.burgas.productservice.model;

import lombok.Builder;

@Builder
public record ProductTypeResponse(
        Long id,
        String name
) {
}
