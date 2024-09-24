package org.burgas.employeeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToMany(
            mappedBy = "position",
            fetch = EAGER
    )
    private List<Employee>employees = new ArrayList<>();
}
