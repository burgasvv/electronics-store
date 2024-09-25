package org.burgas.employeeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProductResponse  {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
    private Boolean archive;
    private String description;
    private ProductTypeResponse productTypeResponse;
}
