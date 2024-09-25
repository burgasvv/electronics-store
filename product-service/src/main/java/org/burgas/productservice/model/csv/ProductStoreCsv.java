package org.burgas.productservice.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreCsv {

    @CsvBindByName(column = "shopId")
    private Long storeId;

    @CsvBindByName(column = "electroItemId")
    private Long productId;

    @CsvBindByName(column = "count")
    private Integer amount;
}
