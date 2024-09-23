package org.burgas.purchaseservice.model;

import lombok.Builder;

import java.sql.Date;

@Builder
public record PurchaseEmployeeResponse(
        Long id,
        String name,
        String surname,
        String patronymic,
        String gender,
        Date birthDate,
        PositionResponse positionResponse
) {
}
