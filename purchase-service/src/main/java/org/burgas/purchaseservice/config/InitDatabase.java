package org.burgas.purchaseservice.config;

import lombok.extern.slf4j.Slf4j;
import org.burgas.purchaseservice.entity.Purchase;
import org.burgas.purchaseservice.entity.PurchaseType;
import org.burgas.purchaseservice.repository.PurchaseRepository;
import org.burgas.purchaseservice.repository.PurchaseTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class InitDatabase {

    @Bean
    public CommandLineRunner insertData(
            PurchaseRepository purchaseRepository,
            PurchaseTypeRepository purchaseTypeRepository
    ) {

        return _ -> {

            PurchaseType papers = PurchaseType.builder().name("Наличные").build();
            PurchaseType cards = PurchaseType.builder().name("Карта").build();

            ArrayList<PurchaseType> purchaseTypes = new ArrayList<>(
                    List.of(papers, cards)
            );

            log.info(
                    "Purchase types added: {}", purchaseTypeRepository.saveAll(purchaseTypes)
            );

            IntStream.range(0, 10)
                    .forEach(
                            _ -> {
                                purchaseRepository.save(
                                        Purchase.builder()
                                                .productId(new Random().nextLong(1, 50))
                                                .employeeId(new Random().nextLong(1, 6))
                                                .purchaseType(
                                                        purchaseTypes.get(new Random().nextInt(0, 1))
                                                )
                                                .storeId(new Random().nextLong(1, 6))
                                                .purchaseDateTime(
                                                        LocalDateTime.of(
                                                                new Random().nextInt(2023, 2025),
                                                                new Random().nextInt(1, 8),
                                                                new Random().nextInt(1, 28),
                                                                new Random().nextInt(9, 21),
                                                                new Random().nextInt(1, 60),
                                                                new Random().nextInt(1, 60)
                                                        )
                                                )
                                                .build()
                                );
                            }
                    );
        };
    }
}
