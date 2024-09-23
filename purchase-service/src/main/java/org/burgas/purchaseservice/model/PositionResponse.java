package org.burgas.purchaseservice.model;

import lombok.Builder;

@Builder
public record PositionResponse(
        Long id,
        String name
) {
}
