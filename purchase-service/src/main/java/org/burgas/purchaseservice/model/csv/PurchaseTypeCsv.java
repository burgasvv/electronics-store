package org.burgas.purchaseservice.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTypeCsv {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "name")
    private String name;
}
