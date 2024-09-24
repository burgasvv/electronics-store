package org.burgas.employeeservice.model.response;

import lombok.Builder;

@Builder
public record PurchaseTypeResponse(
        Long id,
        String name
) {
}
