package org.burgas.productservice.config;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.burgas.productservice.entity.Product;
import org.burgas.productservice.entity.ProductStore;
import org.burgas.productservice.entity.ProductType;
import org.burgas.productservice.repository.ProductRepository;
import org.burgas.productservice.repository.ProductStoreRepository;
import org.burgas.productservice.repository.ProductTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class InitDatabase {

    @Bean
    public CommandLineRunner insertData(
            ProductRepository productRepository,
            ProductTypeRepository productTypeRepository,
            ProductStoreRepository productStoreRepository
    ) {

        return _ -> {

            ProductType tv = ProductType.builder().name("Телевизоры").build();
            ProductType monitors = ProductType.builder().name("Мониторы").build();
            ProductType flashDrives = ProductType.builder().name("Флеш-накопители").build();
            ProductType m2Drives = ProductType.builder().name("ЭТвердотельные накопители").build();
            ProductType graphicCards = ProductType.builder().name("Графические Карты").build();
            ProductType smartphones = ProductType.builder().name("Смартфоны").build();
            ProductType mouses = ProductType.builder().name("Мыши").build();
            ProductType keyboards = ProductType.builder().name("Клавиатуры").build();
            ProductType headphones = ProductType.builder().name("Наушники").build();
            ProductType microphones = ProductType.builder().name("Микрофоны").build();
            ProductType smartWatches = ProductType.builder().name("Умные часы").build();

            ArrayList<ProductType> productTypes = new ArrayList<>(
                    List.of(tv, monitors, flashDrives, m2Drives, graphicCards, smartphones,
                            mouses, keyboards, headphones, microphones, smartWatches
                    )
            );

            log.info("Electronic Types added {}", productTypeRepository.saveAll(productTypes));

            ArrayList<Product> products = new ArrayList<>();
            IntStream.range(0, 50)
                    .forEach(_ -> {
                        Faker faker = new Faker();
                        Product product = productRepository.save(
                                Product.builder()
                                        .name(
                                                UUID.randomUUID().toString().substring(
                                                        0, new Random().nextInt(5, 12)
                                                )
                                        )
                                        .productType(
                                                productTypes.get(
                                                        faker.number().numberBetween(0, productTypes.size())
                                                )
                                        )
                                        .amount(faker.number().numberBetween(0, 10000))
                                        .price(faker.number().numberBetween(2000, 250000))
                                        .archive(true)
                                        .description(faker.lorem().paragraph())
                                        .build()
                        );
                        products.add(product);
                    });

            products.forEach(
                    product -> {

                        ArrayList<Long> storeIds = new ArrayList<>();
                        int nexted = new Random().nextInt(1, 5);

                        for (int i = 0; i < nexted; ++i) {

                            AtomicLong storeId = new AtomicLong(
                                    new Random().nextLong(1, 6)
                            );

                            if (i > 0) {
                                storeIds.forEach(aLong -> {
                                    while (aLong == storeId.get()) {
                                        storeId.set(new Random().nextLong(1, 6));
                                    }
                                });
                            }
                            storeIds.add(storeId.get());
                            int amount = product.getAmount() / nexted;

                            productStoreRepository.save(
                                    ProductStore.builder()
                                            .productId(product.getId())
                                            .storeId(storeId.get())
                                            .amount(amount)
                                            .build()
                            );

                            nexted = new Random().nextInt(1, 5);
                            if (nexted <= i) {
                                while (nexted <= i) {
                                    nexted = new Random().nextInt(1, 5);
                                }
                            }
                        }
                    }
            );
        };
    }
}
