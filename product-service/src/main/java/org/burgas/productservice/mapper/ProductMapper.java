package org.burgas.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.entity.Product;
import org.burgas.productservice.entity.ProductType;
import org.burgas.productservice.model.csv.ProductCsv;
import org.burgas.productservice.model.request.ProductRequest;
import org.burgas.productservice.model.response.ProductResponse;
import org.burgas.productservice.entity.ProductStore;
import org.burgas.productservice.model.response.PurchaseProductResponse;
import org.burgas.productservice.model.response.StoreResponse;
import org.burgas.productservice.repository.ProductStoreRepository;
import org.burgas.productservice.feign.StoreClient;
import org.burgas.productservice.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductTypeMapper productTypeMapper;
    private final StoreClient storeClient;
    private final ProductStoreRepository productStoreRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductResponse toProductResponse(Product product) {

        Map<StoreResponse, ProductStore>storeProductMap = new HashMap<>();

        List<StoreResponse> storeResponses = storeClient.getStoresByProductId(product.getId()).getBody();
        List<ProductStore> productStores = productStoreRepository
                .findProductStoresByProductId(product.getId());

        if (productStores != null || storeResponses != null) {

            storeResponses = storeResponses != null ? storeResponses
                    .stream().distinct()
                    .sorted(Comparator.comparingLong(StoreResponse::getId))
                    .toList() : null;
            productStores = productStores != null ? productStores
                    .stream().distinct()
                    .sorted(Comparator.comparingLong(ProductStore::getStoreId))
                    .toList() : null;

            for (int i = 0; i < (storeResponses != null ? storeResponses.size() : 0); i++) {
                storeProductMap.put(
                        storeResponses.get(i), productStores != null ? productStores.get(i) : null
                );
            }

        }

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productTypeResponse(
                        productTypeMapper.toTypeResponse(
                                productTypeRepository.findById(product.getProductTypeId())
                                        .orElseGet(ProductType::new)
                        )
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
                        productTypeMapper.toTypeResponse(
                                productTypeRepository.findById(product.getProductTypeId())
                                        .orElseGet(ProductType::new)
                        )
                )
                .price(product.getPrice())
                .amount(product.getAmount())
                .archive(product.getArchive())
                .description(product.getDescription())
                .build();
    }

    public Product toProduct(ProductCsv productCsv) {
        return Product.builder()
                .name(productCsv.getName())
                .productTypeId(productCsv.getProductTypeId())
                .price(productCsv.getPrice())
                .amount(productCsv.getAmount())
                .archive(
                        productCsv.getArchive() != 0
                )
                .description(productCsv.getDescription())
                .build();
    }

    public ProductRequest toProductRequest(Product product) {

        List<StoreResponse> storeResponses = storeClient
                .getStoresByProductId(product.getId())
                .getBody();

        List<ProductStore> productStores = productStoreRepository.findProductStoresByProductId(product.getId());

        return ProductRequest.builder()
                .id(product.getId())
                .name(product.getName())
                .productTypeId(product.getProductTypeId())
                .archive(product.getArchive() ? 1 : 0)
                .price(product.getPrice())
                .description(product.getDescription())
                .storeIds(
                        storeResponses != null ?
                                storeResponses.stream()
                                        .map(StoreResponse::getId)
                                        .sorted().toArray(Long[]::new)
                                : null
                )
                .storeProductAmounts(
                        productStores != null ?
                                productStores
                                        .stream().sorted(Comparator.comparing(ProductStore::getStoreId))
                                        .map(ProductStore::getAmount)
                                        .toArray(Integer[]::new)
                                : null
                )
                .build();
    }
}
