package org.burgas.employeeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestEmployeeResponse {

    private Long id;
    private EmployeeResponse employeeResponse;
    private Integer purchasesAmount;
    private Integer fullPrice;
}
