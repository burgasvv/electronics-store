package org.burgas.purchaseservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.exception.PurchaseNotFoundException;
import org.burgas.purchaseservice.mapper.PurchaseMapper;
import org.burgas.purchaseservice.model.PurchaseResponse;
import org.burgas.purchaseservice.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<PurchaseResponse> findAll() {
        return purchaseRepository.findAll()
                .stream().map(purchaseMapper::toPurchaseResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseResponse findById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .map(purchaseMapper::toPurchaseResponse)
                .orElseThrow(
                        () -> new PurchaseNotFoundException(
                                "Покупка по идентификатору: " + purchaseId + " не найдена"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<PurchaseResponse> findByEmployeeId(Long employeeId) {
        return purchaseRepository.findPurchasesByEmployeeId(employeeId)
                .stream().map(purchaseMapper::toPurchaseResponse)
                .toList();
    }
}
