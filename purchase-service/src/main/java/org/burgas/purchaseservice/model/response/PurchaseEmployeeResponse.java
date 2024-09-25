package org.burgas.purchaseservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEmployeeResponse {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String gender;
    private String birthDate;
    private PositionResponse positionResponse;
}
