package org.burgas.purchaseservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.entity.PurchaseType;
import org.burgas.purchaseservice.model.response.PurchaseTypeResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseTypeMapper {

    public PurchaseTypeResponse toPurchaseTypeResponse(
            PurchaseType purchaseType
    ) {
        return PurchaseTypeResponse.builder()
                .id(purchaseType.getId())
                .name(purchaseType.getName())
                .build();
    }
}
