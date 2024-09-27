package org.burgas.productservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;
    private Long productTypeId;
    private String name;
    private Integer price;
    private Integer archive;
    private String description;
    private Long[] storeIds;
    private Integer[] storeProductAmounts;
}
