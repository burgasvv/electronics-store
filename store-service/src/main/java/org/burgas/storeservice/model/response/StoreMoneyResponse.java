package org.burgas.storeservice.model.response;

import lombok.Builder;

@Builder
public record StoreMoneyResponse(
        Long id,
        StoreResponse storeResponse,
        Integer fullSum,
        String purchaseType
) {
}
