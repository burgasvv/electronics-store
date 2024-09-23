package org.burgas.productservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.ProductTypeResponse;
import org.burgas.productservice.exception.ProductTypeNotFoundException;
import org.burgas.productservice.mapper.TypeMapper;
import org.burgas.productservice.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final TypeMapper typeMapper;

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<ProductTypeResponse> findTypesByEmployeeId(Long employeeId) {
        return productTypeRepository.findTypeByEmployeeId(employeeId)
                .stream().map(typeMapper::toTypeResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<ProductTypeResponse> findAll() {
        return productTypeRepository.findAll()
                .stream().map(typeMapper::toTypeResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductTypeResponse findById(Long id) {
        return productTypeRepository.findById(id)
                .map(typeMapper::toTypeResponse)
                .orElseThrow(
                        () -> new ProductTypeNotFoundException(
                                "Тип продукта с идентификатором " + id + " не найден"
                        )
                );
    }
}
