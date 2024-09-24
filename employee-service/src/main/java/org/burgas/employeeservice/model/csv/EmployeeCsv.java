package org.burgas.employeeservice.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCsv {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "lastname")
    private String surname;

    @CsvBindByName(column = "firstname")
    private String name;

    @CsvBindByName(column = "patronymic")
    private String patronymic;

    @CsvBindByName(column = "birthdate")
    @CsvDate("dd.MM.yyyy")
    private Date birthDate;

    @CsvBindByName(column = "position")
    private Long positionId;

    @CsvBindByName(column = "shopid")
    private Long shopId;

    @CsvBindByName(column = "gender")
    private Boolean gender;
}
