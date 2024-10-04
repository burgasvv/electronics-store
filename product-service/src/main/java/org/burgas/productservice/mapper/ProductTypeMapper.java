package org.burgas.productservice.mapper;

import org.burgas.productservice.entity.ProductType;
import org.burgas.productservice.model.csv.ProductTypeCsv;
import org.burgas.productservice.model.request.ProductTypeRequest;
import org.burgas.productservice.model.response.ProductTypeResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeMapper {

    public ProductTypeResponse toProductTypeResponse(ProductType productType) {
        return ProductTypeResponse.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }

    public ProductType toProductType(ProductTypeCsv productTypeCsv) {
        return ProductType.builder()
                .id(productTypeCsv.getId())
                .name(productTypeCsv.getName())
                .build();
    }

    public ProductTypeRequest toProductTypeRequest(ProductType productType) {
        return ProductTypeRequest.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }
}
