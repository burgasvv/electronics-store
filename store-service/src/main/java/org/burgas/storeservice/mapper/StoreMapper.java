package org.burgas.storeservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.entity.Store;
import org.burgas.storeservice.model.PurchaseStoreResponse;
import org.burgas.storeservice.model.StoreResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMapper {

    public StoreResponse toStoreResponse(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }

    public PurchaseStoreResponse toPurchaseStoreResponse(Store store) {
        return PurchaseStoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}
