package org.burgas.employeeservice.model.response;

import lombok.Builder;

@Builder
public record BestJuniorConsultantResponse(
        Long id,
        EmployeeResponse employeeResponse,
        Integer smartWatchesAmount
) {
}
