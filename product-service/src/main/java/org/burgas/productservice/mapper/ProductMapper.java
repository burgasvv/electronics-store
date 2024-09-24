package org.burgas.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.entity.Product;
import org.burgas.productservice.model.csv.ProductCsv;
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

    public Product toProduct(ProductCsv productCsv) {
        return Product.builder()
                .name(productCsv.getName())
                .productType(
                        productTypeRepository.findById(productCsv.getProductTypeId()).orElse(null)
                ).price(productCsv.getPrice())
                .amount(productCsv.getAmount())
                .archive(
                        productCsv.getArchive() != 0
                ).description(productCsv.getDescription())
                .build();
    }
}
