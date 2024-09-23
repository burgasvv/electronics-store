package org.burgas.productservice.mapper;

import org.burgas.productservice.entity.ProductType;
import org.burgas.productservice.model.ProductTypeResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeMapper {

    public ProductTypeResponse toTypeResponse(ProductType productType) {
        return ProductTypeResponse.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }
}
