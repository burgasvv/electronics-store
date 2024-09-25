package org.burgas.employeeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String gender;
    private String birthDate;
    private PositionResponse positionResponse;
    private StoreResponse storeResponse;
    private List<ProductTypeResponse> productTypeResponses;
    private List<PurchaseResponse> purchaseResponses;
}
