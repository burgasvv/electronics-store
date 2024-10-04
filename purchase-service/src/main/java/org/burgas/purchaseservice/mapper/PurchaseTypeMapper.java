package org.burgas.purchaseservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.entity.PurchaseType;
import org.burgas.purchaseservice.model.csv.PurchaseTypeCsv;
import org.burgas.purchaseservice.model.request.PurchaseTypeRequest;
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

    public PurchaseType toPurchaseType(PurchaseTypeCsv purchaseTypeCsv) {
        return PurchaseType.builder()
                .id(purchaseTypeCsv.getId())
                .name(purchaseTypeCsv.getName())
                .build();
    }

    public PurchaseTypeRequest toPurchaseTypeRequest(PurchaseType purchaseType) {
        return PurchaseTypeRequest.builder()
                .id(purchaseType.getId())
                .name(purchaseType.getName())
                .build();
    }
}
