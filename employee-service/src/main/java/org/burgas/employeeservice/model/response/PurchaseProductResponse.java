package org.burgas.employeeservice.model.response;

import lombok.Builder;

@Builder
public record PurchaseProductResponse(
        Long id,
        String name,
        Integer price,
        Integer amount,
        Boolean archive,
        String description,
        ProductTypeResponse productTypeResponse
) {
}
