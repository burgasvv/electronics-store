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
public class ProductCsv {

    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "etypeId")
    private Long productTypeId;

    @CsvBindByName(column = "price")
    private Integer price;

    @CsvBindByName(column = "count")
    private Integer amount;

    @CsvBindByName(column = "archive")
    private Integer archive;

    @CsvBindByName(column = "description")
    private String description;
}
