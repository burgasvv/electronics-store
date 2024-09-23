package org.burgas.employeeservice.model;

import lombok.Builder;

@Builder
public record BestJuniorConsultantResponse(
        Long id,
        EmployeeResponse employeeResponse,
        Integer smartWatchesAmount
) {
}
