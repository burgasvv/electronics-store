package org.burgas.employeeservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmployeeProductTypePK.class)
public class EmployeeProductType {

    @Id
    private Long employeeId;

    @Id
    private Long productTypeId;
}
