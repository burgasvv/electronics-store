package org.burgas.storeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreMoneyResponse {

    private Long id;
    private StoreResponse storeResponse;
    private Integer fullSum;
    private String purchaseType;
}
