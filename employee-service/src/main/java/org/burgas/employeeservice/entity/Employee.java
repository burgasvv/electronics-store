package org.burgas.employeeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgas.employeeservice.model.standart.Gender;

import java.sql.Date;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String surname;

    @Column(nullable = false, length = 100)
    private String patronymic;

    @Column(nullable = false)
    private Date birthDate;

    private Long positionId;
    private Long storeId;

    @Enumerated(STRING)
    private Gender gender;
}
