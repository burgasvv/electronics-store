package org.burgas.purchaseservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.entity.Purchase;
import org.burgas.purchaseservice.feign.EmployeeClient;
import org.burgas.purchaseservice.feign.ProductClient;
import org.burgas.purchaseservice.feign.StoreClient;
import org.burgas.purchaseservice.model.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PurchaseMapper {

    private final ProductClient productClient;
    private final EmployeeClient employeeClient;
    private final StoreClient storeClient;
    private final PurchaseTypeMapper purchaseTypeMapper;

    public PurchaseResponse toPurchaseResponse(Purchase purchase) {
        return PurchaseResponse.builder()
                .id(purchase.getId())
                .purchaseProductResponse(
                        productClient.getProductByPurchaseId(purchase.getId()).getBody()
                )
                .purchaseEmployeeResponse(
                        employeeClient.getEmployeeByPurchaseId(purchase.getId()).getBody()
                )
                .purchaseStoreResponse(
                        storeClient.getStoreByPurchaseId(purchase.getId()).getBody()
                )
                .purchaseTypeResponse(
                        purchaseTypeMapper.toPurchaseTypeResponse(
                                purchase.getPurchaseType()
                        )
                )
                .dateTime(
                        purchase.getPurchaseDateTime().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                        )
                )
                .build();
    }
}
