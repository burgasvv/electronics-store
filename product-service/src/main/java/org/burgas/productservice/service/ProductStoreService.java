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
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@RequiredArgsConstructor
public class ProductStoreService {

    private final ProductStoreRepository productStoreRepository;
    private final ProductStoreMapper productStoreMapper;

    public void saveDataFromCsvFile(MultipartFile multipartFile) throws IOException {

        try (
                SeekableByteChannel byteChannel = Files.newByteChannel(
                        Path.of("C:\\Users\\Burgas\\IdeaProjects\\electronics-store\\employee-service" +
                                "\\src\\main\\resources\\static\\files\\testCsv.txt"),
                        StandardOpenOption.WRITE
                )
        ){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.clear()
                    .put(multipartFile.getBytes())
                    .flip();

            while (byteBuffer.hasRemaining()) {
                byteChannel.write(byteBuffer);
            }
        }

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
