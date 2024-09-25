package org.burgas.employeeservice.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProductTypeCsv {

    @CsvBindByName(column = "employeeId")
    private Long employeeId;

    @CsvBindByName(column = "etype")
    private Long productTypeId;
}
