package org.burgas.storeservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.model.PurchaseStoreResponse;
import org.burgas.storeservice.model.StoreMoneyResponse;
import org.burgas.storeservice.model.StoreResponse;
import org.burgas.storeservice.exception.StoreNotFoundException;
import org.burgas.storeservice.mapper.StoreMapper;
import org.burgas.storeservice.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<StoreResponse> findAll() {
        return storeRepository.findAll()
                .stream().map(storeMapper::toStoreResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public StoreResponse findById(Long id) {
        return storeRepository.findById(id)
                .map(storeMapper::toStoreResponse)
                .orElseThrow(
                        () -> new StoreNotFoundException(
                                "Магазин с идентификатором " + id + " не найден"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<StoreResponse> findStoresByProductId(Long productId) {
        return storeRepository.findStoresByProductId(productId)
                .stream().map(storeMapper::toStoreResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseStoreResponse findStoreByPurchaseId(Long purchaseId) {
        return storeRepository.findStoreByPurchaseId(purchaseId)
                .map(storeMapper::toPurchaseStoreResponse)
                .orElseThrow(
                        () -> new StoreNotFoundException(
                                "Магазин по идентификатору покупки: " + purchaseId + " не найден"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public StoreMoneyResponse findSumPriceInStoreByPurchaseType(
            Long storeId, String purchaseType
    ) {
        StoreResponse storeResponse = storeRepository.findById(storeId)
                .map(storeMapper::toStoreResponse)
                .orElseThrow(
                        () -> new StoreNotFoundException(
                                "Магазин с идентификатором " + storeId + " не найден"
                        )
                );

        return StoreMoneyResponse.builder()
                .storeResponse(storeResponse)
                .fullSum(storeRepository.findSumPriceInStoreByPurchaseType(storeId, purchaseType))
                .purchaseType(purchaseType)
                .build();
    }
}
