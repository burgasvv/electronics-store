package org.burgas.employeeservice.model.response;

import lombok.Builder;

@Builder
public record BestEmployeeResponse(
        Long id,
        EmployeeResponse employeeResponse,
        Integer purchasesAmount,
        Integer fullPrice
) {
}
