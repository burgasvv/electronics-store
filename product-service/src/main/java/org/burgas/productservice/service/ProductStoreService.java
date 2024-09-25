package org.burgas.productservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.productservice.mapper.ProductStoreMapper;
import org.burgas.productservice.model.csv.ProductStoreCsv;
import org.burgas.productservice.repository.ProductStoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class ProductStoreService {

    private final ProductStoreRepository productStoreRepository;
    private final ProductStoreMapper productStoreMapper;

    public void saveDataFromCsvFile(MultipartFile multipartFile) throws IOException {
        productStoreRepository.saveAll(
                new CsvToBeanBuilder<ProductStoreCsv>(
                        new InputStreamReader(multipartFile.getInputStream())
                )
                        .withType(ProductStoreCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(productStoreMapper::toProductStore)
                        .toList()
        );
    }
}
