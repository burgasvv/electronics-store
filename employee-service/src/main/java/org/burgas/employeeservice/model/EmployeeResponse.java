package org.burgas.employeeservice.model;

import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Builder
public record EmployeeResponse(
        Long id,
        String name,
        String surname,
        String patronymic,
        String gender,
        Date birthDate,
        PositionResponse positionResponse,
        StoreResponse storeResponse,
        List<ProductTypeResponse> productTypeResponses,
        List<PurchaseResponse> purchaseResponses
) {
}
