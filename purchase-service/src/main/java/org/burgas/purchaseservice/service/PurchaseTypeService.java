package org.burgas.purchaseservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.exception.PurchaseTypeNotFoundException;
import org.burgas.purchaseservice.mapper.PurchaseTypeMapper;
import org.burgas.purchaseservice.model.PurchaseTypeResponse;
import org.burgas.purchaseservice.repository.PurchaseTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PurchaseTypeService {

    private final PurchaseTypeRepository purchaseTypeRepository;
    private final PurchaseTypeMapper purchaseTypeMapper;

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<PurchaseTypeResponse> findAll() {
        return purchaseTypeRepository.findAll()
                .stream().map(purchaseTypeMapper::toPurchaseTypeResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseTypeResponse findById(Long purchaseTypeId) {
        return purchaseTypeRepository.findById(purchaseTypeId)
                .map(purchaseTypeMapper::toPurchaseTypeResponse)
                .orElseThrow(
                        () -> new PurchaseTypeNotFoundException(
                                "Тип оплаты по идентификатору: " + purchaseTypeId + " не найден"
                        )
                );
    }
}
