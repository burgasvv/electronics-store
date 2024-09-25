package org.burgas.productservice.mapper;

import org.burgas.productservice.entity.ProductType;
import org.burgas.productservice.model.csv.ProductTypeCsv;
import org.burgas.productservice.model.response.ProductTypeResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeMapper {

    public ProductTypeResponse toTypeResponse(ProductType productType) {
        return ProductTypeResponse.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }

    public ProductType toProductType(ProductTypeCsv productTypeCsv) {
        return ProductType.builder()
                .name(productTypeCsv.getName())
                .build();
    }
}
