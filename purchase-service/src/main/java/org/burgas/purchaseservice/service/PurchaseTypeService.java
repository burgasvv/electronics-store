package org.burgas.purchaseservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.mapper.PurchaseTypeMapper;
import org.burgas.purchaseservice.model.csv.PurchaseTypeCsv;
import org.burgas.purchaseservice.model.response.PurchaseTypeResponse;
import org.burgas.purchaseservice.repository.PurchaseTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PurchaseTypeService {

    private final PurchaseTypeRepository purchaseTypeRepository;
    private final PurchaseTypeMapper purchaseTypeMapper;

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<PurchaseTypeResponse> getAllPurchaseTypePages(int page, int size) {
        return purchaseTypeRepository.findAll(getPageRequest(page, size))
                .map(purchaseTypeMapper::toPurchaseTypeResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseTypeResponse findById(Long purchaseTypeId) {
        return purchaseTypeRepository.findById(purchaseTypeId)
                .map(purchaseTypeMapper::toPurchaseTypeResponse)
                .orElseGet(PurchaseTypeResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void safeDataFromCsvFile(MultipartFile multipartFile) throws IOException {
        purchaseTypeRepository.saveAll(
                new CsvToBeanBuilder<PurchaseTypeCsv>(
                        new InputStreamReader(multipartFile.getInputStream())
                )
                        .withType(PurchaseTypeCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(purchaseTypeMapper::toPurchaseType)
                        .toList()
        );
    }
}
