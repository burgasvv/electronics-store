package org.burgas.employeeservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Integer gender;
    private Long positionId;
    private Long storeId;
    private String birthDate;
    private Long[] productTypeIds;
}
