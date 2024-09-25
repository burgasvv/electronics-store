package org.burgas.productservice.config;

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
            ProductType audio = ProductType.builder().name("Аудиотехника").build();
            ProductType smartphones = ProductType.builder().name("Смартфоны").build();
            ProductType plansheti = ProductType.builder().name("Планшеты").build();
            ProductType smartWatches = ProductType.builder().name("Умные часы").build();
            ProductType chainiki = ProductType.builder().name("Чайники").build();
            ProductType pechi = ProductType.builder().name("СВЧ-печи").build();
            ProductType stiralmash = ProductType.builder().name("Стиральные машины").build();
            ProductType holod = ProductType.builder().name("Холодильники").build();
            ProductType kondic = ProductType.builder().name("Кондиционеры").build();
            ProductType water = ProductType.builder().name("Водонагреватели").build();

            ArrayList<ProductType> productTypes = new ArrayList<>(
                    List.of(tv, audio, smartphones, plansheti, smartWatches,
                            chainiki, pechi, stiralmash, holod, kondic, water
                    )
            );

            log.info("Electronic Types added {}", productTypeRepository.saveAll(productTypes));

            Product tv1 = Product.builder().name("Телевизор LED LG 20MT48VF-PZ черный")
                    .productTypeId(1L).price(7899).amount(10).archive(false)
                    .description("HD, 1366x768, DVB-T, DVB-T2, DVB-C, DVB-S2, HDMI х 1, USB х 1").build();

            Product tv2 = Product.builder().name("Телевизор LED BBK 32LEM-1090/T2C белый")
                    .productTypeId(1L).price(10999).amount(0).archive(false)
                    .description("HD, 1366x768, DVB-T, DVB-T2, DVB-C, HDMI х 3, USB х 2, VGA (D-Sub)").build();

            Product tv3 = Product.builder().name("Телевизор LED LG 32LK519PLC белый")
                    .productTypeId(1L).price(15299).amount(0).archive(false)
                    .description("HD, 1366x768, DVB-T2, DVB-C, DVB-S2, HDMI х 2, USB х 1").build();

            Product tv4 = Product.builder().name("Телевизор LED BBK 39LEX-7268/TS2C черный")
                    .productTypeId(1L).price(15399).amount(0).archive(true)
                    .description("HD, 1366x768, DVB-S2, DVB-S, DVB-C, DVB-T2, Wi-Fi, Android TV, HDMI х 3, USB х 2").build();

            Product audio1 = Product.builder().name("Домашняя аудиосистема LG XBOOM CM4360")
                    .productTypeId(2L).price(8999).amount(0).archive(false)
                    .description("2.0, 230 Вт, 100 Гц - 20 кГц, CD, USB, Bluetooth, WMA, MP3, FM, USB").build();

            Product audio2 = Product.builder().name("Домашняя аудиосистема Panasonic SC-HC410EE-S")
                    .productTypeId(2L).price(9850).amount(0).archive(false)
                    .description("2.0, 40 Вт, CD, USB, Bluetooth, MP3, FM, USB").build();

            Product audio3 = Product.builder().name("Домашняя аудиосистема Sony MHC-M20D")
                    .productTypeId(2L).price(20499).amount(0).archive(false)
                    .description("2.0, 390 Вт, караоке, DVD, CD, USB, Bluetooth, MPEG4, WMA, MP3, AAC, AM, FM, USB").build();

            Product audio4 = Product.builder().name("Домашняя аудиосистема Sony Shake-X10D")
                    .productTypeId(2L).price(34999).amount(0).archive(true)
                    .description("2.0, 1200 Вт, 20 Гц - 20 кГц, караоке, DVD, CD, USB, Bluetooth, NFC, MPEG4, WMA, MP3, AAC, FM, USB").build();

            Product smartphone1 = Product.builder().name("Смартфон Samsung Galaxy A01 Core")
                    .productTypeId(3L).price(5899).amount(0).archive(true)
                    .description("16 ГБ синий, 4x1.5 ГГц, 1 Гб, 2 SIM, PLS, 1480x720, камера 8 Мп, 4G, GPS, 3000 мА*ч").build();

            Product smartphone2 = Product.builder().name("Смартфон Nokia C1 Plus")
                    .productTypeId(3L).price(5999).amount(8).archive(false)
                    .description("16 ГБ синий, 4x1.4 ГГц, 1 Гб, 2 SIM, IPS, 1440x720, камера 5 Мп, 4G, GPS, FM, 2500 мА*ч").build();

            Product smartphone3 = Product.builder().name("Смартфон realme C11 (2021)")
                    .productTypeId(3L).price(7999).amount(15).archive(false)
                    .description("32 ГБ синий, 8x1.6 ГГц, 2 Гб, 2 SIM, 1600x720, камера 8 Мп, NFC, 4G, GPS, 5000 мА*ч").build();

            Product smartphone4 = Product.builder().name("Смартфон Xiaomi Redmi 9A")
                    .productTypeId(3L).price(7999).amount(0).archive(false)
                    .description("32 ГБ синий, 8x2 ГГц, 2 Гб, 2 SIM, IPS, 1600x720, камера 13 Мп, 4G, GPS, 5000 мА*ч").build();

            Product smartphone5 = Product.builder().name("Смартфон Samsung Galaxy A02")
                    .productTypeId(3L).price(8299).amount(0).archive(false)
                    .description("32 ГБ черный, 4x1.5 ГГц, 2 Гб, 2 SIM, PLS, 1600x720, камера 13+2 Мп, 4G, GPS, FM, 5000 мА*ч").build();

            Product planshet1 = Product.builder().name("Планшет Samsung GALAXY Tab A 8.0 2019")
                    .productTypeId(4L).price(10999).amount(0).archive(false)
                    .description("32 ГБ черный, 1280x800, TFT PLS, 4х2 ГГц, 2 ГБ, BT, GPS, 5100 мА*ч, Android 9.x+").build();

            Product planshet2 = Product.builder().name("Планшет Huawei T5 10")
                    .productTypeId(4L).price(11949).amount(0).archive(false)
                    .description("16 ГБ 3G, LTE черный, 1920x1200, IPS, 8х2.36 ГГц, 2 ГБ, BT, GPS, 5100 мА*ч, Android 8.x+").build();

            Product planshet3 = Product.builder().name("Планшет Lenovo M10 HD TB-X306F")
                    .productTypeId(4L).price(12499).amount(0).archive(false)
                    .description("32 ГБ серебристый, 1280x800, IPS, 8х1.8 ГГц, 2.3 ГГц, 2 ГБ, BT, GPS, 5000 мА*ч, Android 10.x").build();

            Product planshet4 = Product.builder().name("Планшет Samsung Galaxy Tab A7")
                    .productTypeId(4L).price(16499).amount(0).archive(true)
                    .description("32 ГБ золотистый, 2000x1200, TFT PLS, 8х1.8 ГГц, 2 ГГц, 3 ГБ, BT, GPS, 7040 мА*ч, Android 10.x").build();

            Product smwatch1 = Product.builder().name("Смарт-часы Huawei Watch Fit")
                    .productTypeId(5L).price(7499).amount(0).archive(false)
                    .description("корпус - розовый, ремешок - розовый, Android 5.0 и выше, iOS 9 и выше, крепление - на руку, Bluetooth, Wi-Fi, WR50").build();

            Product smwatch2 = Product.builder().name("Смарт-часы HUAWEI WATCH GT 2 Diana 42 mm")
                    .productTypeId(5L).price(11999).amount(0).archive(false)
                    .description("корпус - белый, Android 4.4 и выше, iOS 9 и выше, крепление - на руку, Bluetooth, IP68").build();

            Product smwatch3 = Product.builder().name("Смарт-часы Samsung Galaxy Watch Active")
                    .productTypeId(5L).price(13799).amount(5).archive(false)
                    .description("корпус - синий, ремешок - синий, iOS, Android, крепление - на руку, Bluetooth, Wi-Fi, NFC, IP68").build();

            Product smwatch4 = Product.builder().name("Смарт-часы Apple Watch Series 3 GPS 38mm")
                    .productTypeId(5L).price(17999).amount(0).archive(false)
                    .description("корпус - серый, ремешок - черный, iOS 13 и выше, крепление - на руку, Wi-Fi, NFC, IP68").build();

            Product chainik1 = Product.builder().name("Электрочайник Redmond SkyKettle RK-G212S")
                    .productTypeId(6L).price(1799).amount(0).archive(false)
                    .description("черный, 1.7 л, 2200 Вт, скрытый нагревательный элемент, фильтр, материал корпуса - термостойкое стекло, пластик").build();

            Product chainik2 = Product.builder().name("Электрочайник Xiaomi Mi Electric Kettle (EU version)")
                    .productTypeId(6L).price(1850).amount(0).archive(false)
                    .description("белый, 1.5 л, 1800 Вт, скрытый нагревательный элемент, материал корпуса - нержавеющая сталь, пластик").build();

            Product chainik3 = Product.builder().name("Электрочайник Viomi V-MK151B")
                    .productTypeId(6L).price(1899).amount(0).archive(false)
                    .description("серебристый, 1.5 л, 1800 Вт, скрытый нагревательный элемент, материал корпуса - нержавеющая сталь").build();

            Product chainik4 = Product.builder().name("Электрочайник Bosch TWK 3A014")
                    .productTypeId(6L).price(1999).amount(2).archive(false)
                    .description("красный, 1.7 л, 2400 Вт, скрытый нагревательный элемент, фильтр, материал корпуса - пластик").build();

            Product pechh1 = Product.builder().name("Микроволновая печь Hisense H20MOBS1H")
                    .productTypeId(7L).price(3999).amount(0).archive(false)
                    .description("черный, 20 л, 700 Вт, переключатели - поворотный механизм, 44.6 см x 24.5 см x 36 см").build();

            Product pechh2 = Product.builder().name("Микроволновая печь Midea MM720CFB")
                    .productTypeId(7L).price(3999).amount(0).archive(false)
                    .description("черный, белый, 20 л, 700 Вт, переключатели - поворотный механизм, 44 см x 25.8 см x 34.5 см").build();

            Product pechh3 = Product.builder().name("Микроволновая печь Hotpoint-Ariston MWHR 3101 B")
                    .productTypeId(7L).price(4499).amount(0).archive(false)
                    .description("черный, 20 л, 700 Вт, переключатели - поворотный механизм, 45.2 см x 26.2 см x 36.5 см").build();

            Product pechh4 = Product.builder().name("Микроволновая печь Winia KOR-660BWW")
                    .productTypeId(7L).price(4799).amount(0).archive(true)
                    .description("белый, 20 л, 700 Вт, переключатели - сенсор, дисплей, 44.9 см x 25.9 см x 31.8 см").build();

            Product stir1 = Product.builder().name("Стиральная машина Candy CSS4 1062D1/2-07")
                    .productTypeId(8L).price(13999).amount(15).archive(false)
                    .description("стирка - 6 кг, фронтальная загрузка, расход 45 л, отжим - 1000 об/мин, программ - 16, пар, 58 дБ, 60 см x 85 см x 40.5 см").build();

            Product stir2 = Product.builder().name("Стиральная машина Beko WDN 735P1 XSW/2-07")
                    .productTypeId(8L).price(14999).amount(0).archive(false)
                    .description("стирка - 7 кг, фронтальная загрузка, расход 52 л, отжим - 1000 об/мин, программ - 15, 63 дБ, 60 см x 84 см x 45 см/мин").build();

            Product stir13= Product.builder().name("Стиральная машина Samsung WF8590NLW8DYLP")
                    .productTypeId(8L).price(19699).amount(0).archive(false)
                    .description("стирка - 6 кг, фронтальная загрузка, расход 48 л, отжим - 1000 об/мин, программ - 8, 60 дБ, 60 см x 85 см x 45 см/мин").build();

            Product stir14= Product.builder().name("Стиральная машина Haier HW60-1229AS")
                    .productTypeId(8L).price(20999).amount(10).archive(false)
                    .description("стирка - 6 кг, фронтальная загрузка, расход 40 л, отжим - 1200 об/мин, программ - 11, пар, 58 дБ, 59.5 см x 85 см x 41 см/мин").build();

            Product holodil1= Product.builder().name("Холодильник с морозильником BEKO RCSK310M20SB")
                    .productTypeId(9L).price(20999).amount(0).archive(false)
                    .description("бежевый, 300 л, внешнее покрытие-пластик, металл, морозильная камера - снизу, размораживание - ручное, 54 см х 184 см х 60 см").build();

            Product holodil2= Product.builder().name("Холодильник с морозильником Bosch KGV36NW1AR")
                    .productTypeId(9L).price(25999).amount(15).archive(false)
                    .description("белый, 317 л, внешнее покрытие-пластик, металл, морозильная камера - снизу, размораживание - ручное, 60 см х 185 см х 65 см").build();

            Product holodil3= Product.builder().name("Холодильник с морозильником Samsung RT25HAR4DWW/WT")
                    .productTypeId(9L).price(28599).amount(0).archive(false)
                    .description("белый, 255 л, внешнее покрытие-пластик, морозильная камера - сверху, размораживание - No Frost, 55.5 см х 163.5 см х 63.7 см").build();

            Product holodil4= Product.builder().name("Холодильник с морозильником Bosch KGV36XL2AR")
                    .productTypeId(9L).price(32999).amount(0).archive(false)
                    .description("серый, 317 л, внешнее покрытие-пластик, металл, морозильная камера - снизу, размораживание - ручное, 60 см х 185 см х 63 см").build();

            Product kondicio1= Product.builder().name("Сплит-система Mystery MSS-09R05")
                    .productTypeId(10L).price(16099).amount(0).archive(false)
                    .description("осушение, обдув (вентиляция), обогрев, охлаждение, до - 25 м?, 37 дБ").build();

            Product kondicio2= Product.builder().name("Сплит-система TCL TAC-09HRA/EF")
                    .productTypeId(10L).price(16899).amount(0).archive(false)
                    .description("обогрев, охлаждение, до - 20 м?").build();

            Product waterrrr1= Product.builder().name("Водонагреватель Electrolux Smartfix 2.0 3.5 S")
                    .productTypeId(11L).price(20990).amount(6).archive(false)
                    .description("проточный, установка вертикальная, нержавеющая сталь, 3.5 кВт, время нагрева - 1 мин, 80°, 27 см x 13.5 см x 10 см").build();

            Product waterrrr2= Product.builder().name("Водонагреватель Thermex Lanza 3500")
                    .productTypeId(11L).price(25990).amount(0).archive(false)
                    .description("проточный, установка горизонтальная, 3.5 кВт, 45°, 29.8 см x 16.8 см x 12.5 см").build();

            log.info(
                    "Products added: {}", productRepository.saveAll(
                            List.of(
                                    tv1,tv2,tv3,tv4,audio1,audio2,audio3,audio4,smartphone1,smartphone2,smartphone3,smartphone4,smartphone5,
                                    planshet1,planshet2,planshet3,planshet4,smwatch1,smwatch2,smwatch3,smwatch4,chainik1,chainik2,
                                    chainik3,chainik4,pechh1,pechh2,pechh3,pechh4,stir1,stir2,stir13,stir14,holodil1,holodil2,holodil3,holodil4,
                                    kondicio1,kondicio2,waterrrr1,waterrrr2
                            )
                    )
            );

            log.info(
                    "Product store added: {}", productStoreRepository.saveAll(
                            List.of(
                                    ProductStore.builder().storeId(1L).productId(1L).amount(4).build(),
                                    ProductStore.builder().storeId(1L).productId(20L).amount(5).build(),
                                    ProductStore.builder().storeId(2L).productId(10L).amount(3).build(),
                                    ProductStore.builder().storeId(2L).productId(40L).amount(5).build(),
                                    ProductStore.builder().storeId(3L).productId(30L).amount(15).build(),
                                    ProductStore.builder().storeId(3L).productId(40L).amount(1).build(),
                                    ProductStore.builder().storeId(4L).productId(10L).amount(5).build(),
                                    ProductStore.builder().storeId(4L).productId(1L).amount(6).build(),
                                    ProductStore.builder().storeId(5L).productId(33L).amount(10).build(),
                                    ProductStore.builder().storeId(5L).productId(11L).amount(15).build(),
                                    ProductStore.builder().storeId(6L).productId(35L).amount(15).build(),
                                    ProductStore.builder().storeId(6L).productId(25L).amount(2).build()
                            )
                    )
            );

        };
    }
}
