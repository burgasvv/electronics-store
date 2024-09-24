package org.burgas.employeeservice.model.response;

import lombok.Builder;

import java.sql.Date;

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
