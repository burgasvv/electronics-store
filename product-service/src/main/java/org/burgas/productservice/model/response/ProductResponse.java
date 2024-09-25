package org.burgas.productservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgas.productservice.entity.ProductStore;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
    private Boolean archive;
    private String description;
    private ProductTypeResponse productTypeResponse;
    private Map<StoreResponse, ProductStore> storeProductMap;
}
