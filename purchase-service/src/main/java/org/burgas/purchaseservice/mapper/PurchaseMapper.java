package org.burgas.purchaseservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.entity.Purchase;
import org.burgas.purchaseservice.entity.PurchaseType;
import org.burgas.purchaseservice.feign.EmployeeClient;
import org.burgas.purchaseservice.feign.ProductClient;
import org.burgas.purchaseservice.feign.StoreClient;
import org.burgas.purchaseservice.model.csv.PurchaseCsv;
import org.burgas.purchaseservice.model.response.PurchaseEmployeeResponse;
import org.burgas.purchaseservice.model.response.PurchaseProductResponse;
import org.burgas.purchaseservice.model.response.PurchaseResponse;
import org.burgas.purchaseservice.model.response.PurchaseStoreResponse;
import org.burgas.purchaseservice.repository.PurchaseTypeRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PurchaseMapper {

    private final ProductClient productClient;
    private final EmployeeClient employeeClient;
    private final StoreClient storeClient;
    private final PurchaseTypeMapper purchaseTypeMapper;
    private final PurchaseTypeRepository purchaseTypeRepository;

    public PurchaseResponse toPurchaseResponse(Purchase purchase) {

        PurchaseProductResponse ppr = productClient.getProductByPurchaseId(purchase.getId()).getBody();
        PurchaseEmployeeResponse per = employeeClient.getEmployeeByPurchaseId(purchase.getId()).getBody();
        PurchaseStoreResponse psr = storeClient.getStoreByPurchaseId(purchase.getId()).getBody();

        return PurchaseResponse.builder()
                .id(purchase.getId())
                .purchaseProductResponse(
                        ppr == null ? PurchaseProductResponse.builder().build() : ppr
                )
                .purchaseEmployeeResponse(
                        per == null ? PurchaseEmployeeResponse.builder().build() : per
                )
                .purchaseStoreResponse(
                        psr == null ? PurchaseStoreResponse.builder().build() : psr
                )
                .purchaseTypeResponse(
                        purchaseTypeMapper.toPurchaseTypeResponse(
                                purchase.getPurchaseType() == null ?
                                        PurchaseType.builder().build() : purchase.getPurchaseType()
                        )
                )
                .dateTime(
                        purchase.getPurchaseDateTime().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                        )
                )
                .build();
    }

    public Purchase toPurchase(PurchaseCsv purchaseCsv) {
        return Purchase.builder()
                .productId(purchaseCsv.getProductId())
                .employeeId(purchaseCsv.getEmployeeId())
                .storeId(purchaseCsv.getStoreId())
                .purchaseDateTime(purchaseCsv.getPurchaseDate())
                .purchaseType(
                        purchaseTypeRepository.findById(purchaseCsv.getPurchaseTypeId()).orElse(null)
                )
                .build();
    }
}
