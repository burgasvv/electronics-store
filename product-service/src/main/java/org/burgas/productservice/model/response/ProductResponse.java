package org.burgas.productservice.model.response;

import lombok.Builder;
import org.burgas.productservice.entity.ProductStore;

import java.util.Map;

@Builder
public record ProductResponse(
        Long id,
        String name,
        Integer price,
        Integer amount,
        Boolean archive,
        String description,
        ProductTypeResponse productTypeResponse,
        Map<StoreResponse, ProductStore> storeProductMap
) {
}
