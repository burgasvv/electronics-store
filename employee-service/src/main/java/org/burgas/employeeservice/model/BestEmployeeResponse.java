package org.burgas.employeeservice.model;

import lombok.Builder;

@Builder
public record BestEmployeeResponse(
        Long id,
        EmployeeResponse employeeResponse,
        Integer purchasesAmount,
        Integer fullPrice
) {
}
