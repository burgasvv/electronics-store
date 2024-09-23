package org.burgas.employeeservice.model;

import lombok.Builder;

@Builder
public record PositionResponse(
        Long id,
        String name
) {
}
