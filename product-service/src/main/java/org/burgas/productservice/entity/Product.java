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
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    private Integer price;
    private Integer amount;
    private Boolean archive;

    @Column(columnDefinition = "TEXT")
    private String description;
}
