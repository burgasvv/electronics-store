package org.burgas.productservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.response.ProductTypeResponse;
import org.burgas.productservice.exception.ProductTypeNotFoundException;
import org.burgas.productservice.mapper.ProductTypeMapper;
import org.burgas.productservice.repository.ProductTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<ProductTypeResponse> findAllProductTypePages(int page, int size) {
        return productTypeRepository.findAll(getPageRequest(page, size))
                .map(productTypeMapper::toTypeResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<ProductTypeResponse> findTypesByEmployeeId(Long employeeId) {
        return productTypeRepository.findTypeByEmployeeId(employeeId)
                .stream().map(productTypeMapper::toTypeResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<ProductTypeResponse> findAll() {
        return productTypeRepository.findAll()
                .stream().map(productTypeMapper::toTypeResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductTypeResponse findById(Long id) {
        return productTypeRepository.findById(id)
                .map(productTypeMapper::toTypeResponse)
                .orElseThrow(
                        () -> new ProductTypeNotFoundException(
                                "Тип продукта с идентификатором " + id + " не найден"
                        )
                );
    }
}
