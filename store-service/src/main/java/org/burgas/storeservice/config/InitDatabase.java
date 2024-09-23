package org.burgas.storeservice.config;

import lombok.extern.slf4j.Slf4j;
import org.burgas.storeservice.entity.Store;
import org.burgas.storeservice.repository.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class InitDatabase {

    @Bean
    public CommandLineRunner insertData(StoreRepository storeRepository) {
        return _ -> {

            Store stock = Store.builder().name("Магазин Склад").
                    address("г. Новосибирск, ул. Фабричная, д.2")
                    .build();
            Store red = Store.builder().name("Филиал на Красном")
                    .address("г. Новосиьирск, Красный пр-т, д. 13")
                    .build();
            Store lenina = Store.builder().name("Филиал на Ленина")
                    .address("г. Новосибирск, ул. Ленина, д. 44")
                    .build();
            Store kirova = Store.builder().name("Филиал на Кирова")
                    .address("г. Новосибирск, ул. Кирова, д. 10")
                    .build();
            Store michurina = Store.builder().name("Филиал на Мичурина")
                    .address("г. Новосибирск, ул. Мичурина, д. 12")
                    .build();
            Store marksa = Store.builder().name("Филиал на Маркса")
                    .address("г. Новосибирск, Площадь Карла Маркса, д. 68")
                    .build();

            log.info(
                    "Stores added {}: ", storeRepository.saveAll(
                            List.of(stock, red, lenina, kirova, michurina, marksa)
                    )
            );
        };
    }
}
