package org.burgas.purchaseservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.exception.PurchaseTypeNotFoundException;
import org.burgas.purchaseservice.mapper.PurchaseTypeMapper;
import org.burgas.purchaseservice.model.response.PurchaseTypeResponse;
import org.burgas.purchaseservice.repository.PurchaseTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PurchaseTypeService {

    private final PurchaseTypeRepository purchaseTypeRepository;
    private final PurchaseTypeMapper purchaseTypeMapper;

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<PurchaseTypeResponse> getAllPurchaseTypePages(int page, int size) {
        return purchaseTypeRepository.findAll(getPageRequest(page, size))
                .map(purchaseTypeMapper::toPurchaseTypeResponse);
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
