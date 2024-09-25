package org.burgas.employeeservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.entity.EmployeeProductType;
import org.burgas.employeeservice.model.csv.EmployeeProductTypeCsv;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProductTypeMapper {

    public EmployeeProductType toEmployeeProductType(
            EmployeeProductTypeCsv employeeProductTypeCsv
    ) {
        return EmployeeProductType.builder()
                .employeeId(employeeProductTypeCsv.getEmployeeId())
                .productTypeId(employeeProductTypeCsv.getProductTypeId())
                .build();
    }
}
