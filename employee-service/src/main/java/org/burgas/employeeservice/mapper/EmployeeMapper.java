package org.burgas.employeeservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.entity.Employee;
import org.burgas.employeeservice.feign.PurchaseClient;
import org.burgas.employeeservice.model.EmployeeResponse;
import org.burgas.employeeservice.feign.ProductTypeClient;
import org.burgas.employeeservice.feign.StoreClient;
import org.burgas.employeeservice.model.PurchaseEmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeMapper {

    private final PositionMapper positionMapper;
    private final StoreClient storeClient;
    private final ProductTypeClient productTypeClient;
    private final PurchaseClient purchaseClient;

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .patronymic(employee.getPatronymic())
                .gender(employee.getGender().getName())
                .birthDate(employee.getBirthDate())
                .positionResponse(
                        positionMapper.toPositionResponse(employee.getPosition())
                ).storeResponse(
                        storeClient.getStoreById(employee.getStoreId()).getBody()
                ).productTypeResponses(
                        Objects.requireNonNull(
                                    productTypeClient.getEmployeeProductTypes(employee.getId()).getBody()
                                )
                                .stream().distinct().toList()
                ).purchaseResponses(
                        purchaseClient.getPurchasesByEmployee(employee.getId()).getBody()
                )
                .build();
    }

    public PurchaseEmployeeResponse toPurchaseEmployeeResponse(Employee employee) {
        return PurchaseEmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .patronymic(employee.getPatronymic())
                .gender(employee.getGender().getName())
                .birthDate(employee.getBirthDate())
                .positionResponse(
                        positionMapper.toPositionResponse(employee.getPosition())
                )
                .build();
    }
}
