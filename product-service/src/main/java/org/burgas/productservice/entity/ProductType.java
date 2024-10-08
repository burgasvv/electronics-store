package org.burgas.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;
}
