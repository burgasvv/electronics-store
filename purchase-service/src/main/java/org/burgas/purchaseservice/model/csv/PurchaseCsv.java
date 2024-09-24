package org.burgas.purchaseservice.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCsv {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "electroId")
    private Long productId;

    @CsvBindByName(column = "employeeId")
    private Long employeeId;

    @CsvBindByName(column = "purchaseDate")
    @CsvDate("dd.MM.yyyy HH:mm")
    private LocalDateTime purchaseDate;

    @CsvBindByName(column = "typeId")
    private Long purchaseTypeId;

    @CsvBindByName(column = "shopId")
    private Long storeId;
}
