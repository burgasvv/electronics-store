package org.burgas.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.entity.Product;
import org.burgas.productservice.model.ProductResponse;
import org.burgas.productservice.entity.ProductStore;
import org.burgas.productservice.model.PurchaseProductResponse;
import org.burgas.productservice.model.StoreResponse;
import org.burgas.productservice.repository.ProductStoreRepository;
import org.burgas.productservice.feign.StoreClient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductTypeMapper productTypeMapper;
    private final StoreClient storeClient;
    private final ProductStoreRepository productStoreRepository;

    public ProductResponse toProductResponse(Product product) {

        Map<StoreResponse, ProductStore>storeProductMap = new HashMap<>();

        List<StoreResponse> storeResponses = Objects.requireNonNull(
                storeClient.getStoresByProductId(product.getId()).getBody()
        )
                .stream().distinct()
                .sorted(Comparator.comparingLong(StoreResponse::id))
                .toList();

        List<ProductStore> productStores = productStoreRepository
                .findProductStoresByProductId(product.getId())
                .stream().distinct()
                .sorted(Comparator.comparingLong(ProductStore::getStoreId))
                .toList();

        for (int i = 0; i < Objects.requireNonNull(storeResponses).size(); i++) {
            storeProductMap.put(
                    storeResponses.get(i), productStores.get(i)
            );
        }

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productTypeResponse(
                        productTypeMapper.toTypeResponse(product.getProductType())
                )
                .price(product.getPrice())
                .amount(product.getAmount())
                .archive(product.getArchive())
                .description(product.getDescription())
                .storeProductMap(storeProductMap)
                .build();
    }

    public PurchaseProductResponse toPurchaseProductResponse(Product product) {
        return PurchaseProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productTypeResponse(
                        productTypeMapper.toTypeResponse(product.getProductType())
                )
                .price(product.getPrice())
                .amount(product.getAmount())
                .archive(product.getArchive())
                .description(product.getDescription())
                .build();
    }
}
