package org.burgas.purchaseservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.exception.PurchaseNotFoundException;
import org.burgas.purchaseservice.mapper.PurchaseMapper;
import org.burgas.purchaseservice.model.PurchaseResponse;
import org.burgas.purchaseservice.repository.PurchaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<PurchaseResponse> getAllPurchasePages(int page, int size) {
        return purchaseRepository.findAll(getPageRequest(page, size))
                .map(purchaseMapper::toPurchaseResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<PurchaseResponse> sortPurchasesByDate(final String sort, int page, int size) {
        Page<PurchaseResponse> purchaseResponses = null;
        if (
                sort.equalsIgnoreCase("new")
        ) {
            purchaseResponses = purchaseRepository.findAll(
                    PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "purchaseDateTime"))
            )
                    .map(purchaseMapper::toPurchaseResponse);
        }
        else if (
                sort.equalsIgnoreCase("old")
        ) {
            purchaseResponses = purchaseRepository.findAll(
                            PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "purchaseDateTime"))
                    )
                    .map(purchaseMapper::toPurchaseResponse);
        }
        return purchaseResponses;
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
