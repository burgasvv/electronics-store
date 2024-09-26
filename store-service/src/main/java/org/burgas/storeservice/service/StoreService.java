package org.burgas.storeservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.mapper.StoreMapper;
import org.burgas.storeservice.model.csv.StoreCsv;
import org.burgas.storeservice.model.response.PurchaseStoreResponse;
import org.burgas.storeservice.model.response.StoreMoneyResponse;
import org.burgas.storeservice.model.response.StoreResponse;
import org.burgas.storeservice.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<StoreResponse> findAllStorePages(int page, int size) {
        return storeRepository.findAll(getPageRequest(page , size))
                .map(storeMapper::toStoreResponse);
    }

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
                .orElseGet(StoreResponse::new);
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
                .orElseGet(PurchaseStoreResponse::new);
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
                .orElseGet(StoreResponse::new);

        return StoreMoneyResponse.builder()
                .storeResponse(storeResponse)
                .fullSum(storeRepository.findSumPriceInStoreByPurchaseType(storeId, purchaseType))
                .purchaseType(purchaseType)
                .build();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<PurchaseStoreResponse> findStoresByProductIdInStock(Long productId) {
        return storeRepository.findStoresByProductIdInStock(productId)
                .stream().map(storeMapper::toPurchaseStoreResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void saveDataFromCsvFile(MultipartFile multipartFile) throws IOException {
        storeRepository.saveAll(
                new CsvToBeanBuilder<StoreCsv>(
                        new InputStreamReader(multipartFile.getInputStream())
                )
                        .withType(StoreCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(storeMapper::toStore)
                        .toList()
        );
    }
}
