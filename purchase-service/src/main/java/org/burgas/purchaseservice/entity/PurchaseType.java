package org.burgas.purchaseservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToMany(
            mappedBy = "purchaseType",
            cascade = {PERSIST, MERGE},
            fetch = EAGER
    )
    private List<Purchase>purchases = new ArrayList<>();
}
