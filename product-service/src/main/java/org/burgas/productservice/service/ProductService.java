package org.burgas.productservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.ProductResponse;
import org.burgas.productservice.exception.ProductNotFoundException;
import org.burgas.productservice.mapper.ProductMapper;
import org.burgas.productservice.model.PurchaseProductResponse;
import org.burgas.productservice.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream().map(productMapper::toProductResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Продукт с идентификатором " + id + " не найден"
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
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Продукт по идентификатору покупки: " + purchaseId + " не найден"
                        )
                );

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
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Продукт по идентификатору покупки: " + purchaseId +
                                ", и сотрудника: " + employeeId + " не найден"
                        )
                );
    }
}
