package org.burgas.employeeservice.model;

import lombok.Builder;

@Builder
public record PurchaseTypeResponse(
        Long id,
        String name
) {
}
