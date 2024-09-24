package org.burgas.employeeservice.model.response;

import lombok.Builder;

@Builder
public record ProductTypeResponse(
        Long id,
        String name
) {
}
