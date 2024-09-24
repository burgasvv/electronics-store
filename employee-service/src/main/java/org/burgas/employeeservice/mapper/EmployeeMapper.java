package org.burgas.employeeservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.entity.Employee;
import org.burgas.employeeservice.feign.ProductTypeClient;
import org.burgas.employeeservice.feign.PurchaseClient;
import org.burgas.employeeservice.feign.StoreClient;
import org.burgas.employeeservice.model.csv.EmployeeCsv;
import org.burgas.employeeservice.model.response.EmployeeResponse;
import org.burgas.employeeservice.model.response.PurchaseEmployeeResponse;
import org.burgas.employeeservice.model.standart.Gender;
import org.burgas.employeeservice.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeMapper {

    private final PositionMapper positionMapper;
    private final StoreClient storeClient;
    private final ProductTypeClient productTypeClient;
    private final PurchaseClient purchaseClient;
    private final PositionRepository positionRepository;

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .patronymic(employee.getPatronymic())
                .gender(employee.getGender().getName())
                .birthDate(
                        employee.getBirthDate().toLocalDate().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        )
                )
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
                .birthDate(
                        employee.getBirthDate().toLocalDate().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        )
                )
                .positionResponse(
                        positionMapper.toPositionResponse(employee.getPosition())
                )
                .build();
    }

    public Employee toEmployee(EmployeeCsv employeeCsv) {
        return Employee.builder()
                .surname(employeeCsv.getSurname())
                .name(employeeCsv.getName())
                .patronymic(employeeCsv.getPatronymic())
                .position(
                        positionRepository.findById(employeeCsv.getPositionId()).orElse(null)
                ).storeId(employeeCsv.getShopId())
                .birthDate(employeeCsv.getBirthDate())
                .gender(
                        employeeCsv.getGender() ? Gender.MALE : Gender.FEMALE
                )
                .build();
    }
}
