package org.burgas.purchaseservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Long employeeId;
    private Long storeId;
    private LocalDateTime purchaseDateTime;

    @ManyToOne
    @JoinColumn(name = "purchase_type_id")
    private PurchaseType purchaseType;
}
