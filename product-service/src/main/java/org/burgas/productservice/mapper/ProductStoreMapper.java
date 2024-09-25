package org.burgas.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.entity.ProductStore;
import org.burgas.productservice.model.csv.ProductStoreCsv;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStoreMapper {

    public ProductStore toProductStore(ProductStoreCsv productStoreCsv) {
        return ProductStore.builder()
                .storeId(productStoreCsv.getStoreId())
                .productId(productStoreCsv.getProductId())
                .amount(productStoreCsv.getAmount())
                .build();
    }
}
