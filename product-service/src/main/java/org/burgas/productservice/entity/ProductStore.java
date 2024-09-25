package org.burgas.productservice.entity;

import jakarta.persistence.Column;
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
@IdClass(ProductStorePK.class)
public class ProductStore {

    @Id
    private Long productId;

    @Id
    private Long storeId;

    @Column(nullable = false)
    private Integer amount;
}
