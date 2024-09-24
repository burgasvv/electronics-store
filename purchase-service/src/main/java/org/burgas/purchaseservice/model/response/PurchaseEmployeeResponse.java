package org.burgas.purchaseservice.model.response;

import lombok.Builder;

@Builder
public record PurchaseEmployeeResponse(
        Long id,
        String name,
        String surname,
        String patronymic,
        String gender,
        String birthDate,
        PositionResponse positionResponse
) {
}
