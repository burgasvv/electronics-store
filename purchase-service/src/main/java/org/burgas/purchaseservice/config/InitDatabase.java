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
            PurchaseType onlinePayment = PurchaseType.builder().name("Онлайн-оплата").build();
            PurchaseType credit = PurchaseType.builder().name("Кредит").build();
            PurchaseType wtf = PurchaseType.builder().name("Рассрочка").build();

            ArrayList<PurchaseType> purchaseTypes = new ArrayList<>(
                    List.of(papers, cards, onlinePayment, credit, wtf)
            );

            log.info(
                    "Purchase types added: {}", purchaseTypeRepository.saveAll(purchaseTypes)
            );

            log.info(
                    "Purchase added: {}", purchaseRepository.saveAll(
                            List.of(
                                    Purchase.builder().productId(1L).employeeId(1L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,16,10,15)
                                            ).purchaseTypeId(1L).storeId(1L).build(),

                                    Purchase.builder().productId(8L).employeeId(1L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,17,12,55)
                                            ).purchaseTypeId(2L).storeId(1L).build(),

                                    Purchase.builder().productId(12L).employeeId(1L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,26,15,21)
                                            ).purchaseTypeId(1L).storeId(1L).build(),

                                    Purchase.builder().productId(15L).employeeId(1L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,30,17,35)
                                            ).purchaseTypeId(2L).storeId(1L).build(),

                                    Purchase.builder().productId(24L).employeeId(2L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,14,10,7)
                                            ).purchaseTypeId(1L).storeId(1L).build(),

                                    Purchase.builder().productId(27L).employeeId(2L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,21,15,23)
                                            ).purchaseTypeId(4L).storeId(1L).build(),

                                    Purchase.builder().productId(32L).employeeId(2L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,30,16,51)
                                            ).purchaseTypeId(3L).storeId(1L).build(),

                                    Purchase.builder().productId(20L).employeeId(3L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,16,10,12)
                                            ).purchaseTypeId(1L).storeId(2L).build(),

                                    Purchase.builder().productId(16L).employeeId(3L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,18,11,13)
                                            ).purchaseTypeId(2L).storeId(2L).build(),

                                    Purchase.builder().productId(12L).employeeId(3L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,19,14,17)
                                            ).purchaseTypeId(3L).storeId(2L).build(),

                                    Purchase.builder().productId(35L).employeeId(4L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,11,11,14)
                                            ).purchaseTypeId(1L).storeId(2L).build(),

                                    Purchase.builder().productId(39L).employeeId(4L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,12,14,22)
                                            ).purchaseTypeId(2L).storeId(2L).build(),

                                    Purchase.builder().productId(41L).employeeId(4L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,10,15,33)
                                            ).purchaseTypeId(3L).storeId(2L).build(),

                                    Purchase.builder().productId(10L).employeeId(5L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,23,10,3)
                                            ).purchaseTypeId(1L).storeId(3L).build(),

                                    Purchase.builder().productId(3L).employeeId(6L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,16,11,12)
                                            ).purchaseTypeId(1L).storeId(3L).build(),

                                    Purchase.builder().productId(7L).employeeId(6L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,17,13,40)
                                            ).purchaseTypeId(2L).storeId(3L).build(),

                                    Purchase.builder().productId(15L).employeeId(6L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,19,12,33)
                                            ).purchaseTypeId(3L).storeId(3L).build(),

                                    Purchase.builder().productId(26L).employeeId(7L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,15,13,11)
                                            ).purchaseTypeId(1L).storeId(4L).build(),

                                    Purchase.builder().productId(30L).employeeId(7L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,15,15,33)
                                            ).purchaseTypeId(2L).storeId(4L).build(),

                                    Purchase.builder().productId(38L).employeeId(8L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,25,10,25)
                                            ).purchaseTypeId(1L).storeId(4L).build(),

                                    Purchase.builder().productId(40L).employeeId(8L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,27,11,35)
                                            ).purchaseTypeId(2L).storeId(4L).build(),

                                    Purchase.builder().productId(6L).employeeId(9L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,10,13,23)
                                            ).purchaseTypeId(1L).storeId(5L).build(),

                                    Purchase.builder().productId(7L).employeeId(9L)
                                            .purchaseDateTime(
                                                    LocalDateTime.of(2024,3,13,14,47)
                                            ).purchaseTypeId(1L).storeId(5L).build()
                            )
                    )
            );

        };
    }
}
