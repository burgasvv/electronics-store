package org.burgas.productservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.productservice.mapper.ProductMapper;
import org.burgas.productservice.model.csv.ProductCsv;
import org.burgas.productservice.model.response.ProductResponse;
import org.burgas.productservice.model.response.PurchaseProductResponse;
import org.burgas.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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
}
