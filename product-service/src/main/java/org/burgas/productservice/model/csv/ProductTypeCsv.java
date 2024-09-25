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
public class ProductTypeCsv {

    @CsvBindByName(column = "id")
    private long id;

    @CsvBindByName(column = "name")
    private String name;
}
