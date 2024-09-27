package org.burgas.storeservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.entity.Store;
import org.burgas.storeservice.model.csv.StoreCsv;
import org.burgas.storeservice.model.request.StoreRequest;
import org.burgas.storeservice.model.response.PurchaseStoreResponse;
import org.burgas.storeservice.model.response.StoreResponse;
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

    public Store toStore(StoreCsv storeCsv) {
        return Store.builder()
                .name(storeCsv.getName())
                .address(storeCsv.getAddress())
                .build();
    }

    public StoreRequest toStoreRequest(Store store) {
        return StoreRequest.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}
