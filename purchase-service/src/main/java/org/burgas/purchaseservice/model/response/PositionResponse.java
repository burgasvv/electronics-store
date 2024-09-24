package org.burgas.purchaseservice.model.response;

import lombok.Builder;

@Builder
public record PositionResponse(
        Long id,
        String name
) {
}
