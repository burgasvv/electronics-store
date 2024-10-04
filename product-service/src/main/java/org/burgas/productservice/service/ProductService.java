package org.burgas.productservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.productservice.entity.Product;
import org.burgas.productservice.entity.ProductStore;
import org.burgas.productservice.exception.ProductNotFoundException;
import org.burgas.productservice.feign.StoreClient;
import org.burgas.productservice.mapper.ProductMapper;
import org.burgas.productservice.model.csv.ProductCsv;
import org.burgas.productservice.model.request.ProductRequest;
import org.burgas.productservice.model.response.ProductResponse;
import org.burgas.productservice.model.response.PurchaseProductResponse;
import org.burgas.productservice.model.response.StoreResponse;
import org.burgas.productservice.repository.ProductRepository;
import org.burgas.productservice.repository.ProductStoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductStoreRepository productStoreRepository;
    private final ProductMapper productMapper;
    private final StoreClient storeClient;

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<ProductResponse> findAllPages(int page, int size) {
        return productRepository.findAll(getPageRequest(page, size))
                .map(productMapper::toProductResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<ProductResponse> findPagesByProductTypeId(Long productTypeId, int page, int size) {
        return productRepository.findProductByProductTypeId(
                        productTypeId, getPageRequest(page, size)
                )
                .map(productMapper::toProductResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseGet(ProductResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductRequest findProductRequestById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductRequest)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Электротовар с идентификатором " + productId + " не найден"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseProductResponse findProductByPurchaseId(Long purchaseId) {
        return productRepository.findProductByPurchaseId(purchaseId)
                .map(productMapper::toPurchaseProductResponse)
                .orElseGet(PurchaseProductResponse::new);

    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseProductResponse findProductByEmployeeIdAndPurchaseId(
            Long employeeId, Long purchaseId
    ) {
        return productRepository.findProductByEmployeeIdAndPurchaseId(employeeId, purchaseId)
                .map(productMapper::toPurchaseProductResponse)
                .orElseGet(PurchaseProductResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void saveDataFromCsvFile(MultipartFile multipartFile) throws IOException {
        productRepository.saveAll(
                new CsvToBeanBuilder<ProductCsv>(
                        new InputStreamReader(multipartFile.getInputStream())
                )
                        .withType(ProductCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(productMapper::toProduct)
                        .toList()
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<StoreResponse> getStoresByIds(Long[] storeIds) {
        List<StoreResponse> storeResponses = new ArrayList<>();
        Arrays.stream(storeIds)
                .forEach(
                        aLong -> storeResponses.add(
                                storeClient.getStoreById(aLong).getBody()
                        )
                );
        return storeResponses;
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productRepository.save(
                Product.builder()
                        .name(productRequest.getName())
                        .productTypeId(productRequest.getProductTypeId())
                        .price(productRequest.getPrice())
                        .archive(productRequest.getArchive() != 0)
                        .amount(
                                Arrays.stream(productRequest.getStoreProductAmounts())
                                        .reduce(Integer::sum).orElse(0)
                        ).description(productRequest.getDescription())
                        .build()
        );
        for (int i = 0; i < productRequest.getStoreIds().length; i++) {
            productStoreRepository.save(
                    ProductStore.builder()
                            .productId(product.getId())
                            .storeId(productRequest.getStoreIds()[i])
                            .amount(productRequest.getStoreProductAmounts()[i])
                            .build()
            );
        }

        return productMapper.toProductResponse(product);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductResponse editProduct(ProductRequest productRequest) {
        Product product = productRepository.save(
                Product.builder()
                        .id(productRequest.getId())
                        .name(productRequest.getName())
                        .productTypeId(productRequest.getProductTypeId())
                        .price(productRequest.getPrice())
                        .archive(productRequest.getArchive() != 0)
                        .amount(
                                Arrays.stream(productRequest.getStoreProductAmounts())
                                        .reduce(Integer::sum).orElse(0)
                        ).description(productRequest.getDescription())
                        .build()
        );

        productStoreRepository.deleteProductStoresByProductId(product.getId());

        for (int i = 0; i < productRequest.getStoreIds().length; i++) {
            productStoreRepository.save(
                    ProductStore.builder()
                            .productId(product.getId())
                            .storeId(productRequest.getStoreIds()[i])
                            .amount(productRequest.getStoreProductAmounts()[i])
                            .build()
            );
        }

        return productMapper.toProductResponse(product);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void deleteProduct(Long productId) {
        productStoreRepository.deleteProductStoresByProductId(productId);
        productRepository.deleteById(productId);
    }
}
