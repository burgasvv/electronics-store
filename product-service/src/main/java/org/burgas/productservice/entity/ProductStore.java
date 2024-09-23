package org.burgas.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStore {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Long storeId;
    private Integer amount;
}
