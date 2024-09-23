package org.burgas.employeeservice.model;

import lombok.Builder;

@Builder
public record ProductTypeResponse(
        Long id,
        String name
) {
}
