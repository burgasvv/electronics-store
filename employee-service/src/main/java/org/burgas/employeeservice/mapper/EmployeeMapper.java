package org.burgas.employeeservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.entity.Employee;
import org.burgas.employeeservice.entity.Position;
import org.burgas.employeeservice.feign.ProductTypeClient;
import org.burgas.employeeservice.feign.PurchaseClient;
import org.burgas.employeeservice.feign.StoreClient;
import org.burgas.employeeservice.model.csv.EmployeeCsv;
import org.burgas.employeeservice.model.request.EmployeeRequest;
import org.burgas.employeeservice.model.response.EmployeeResponse;
import org.burgas.employeeservice.model.response.ProductTypeResponse;
import org.burgas.employeeservice.model.response.PurchaseEmployeeResponse;
import org.burgas.employeeservice.model.standart.Gender;
import org.burgas.employeeservice.repository.EmployeeProductTypeRepository;
import org.burgas.employeeservice.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeMapper {

    private final PositionMapper positionMapper;
    private final StoreClient storeClient;
    private final ProductTypeClient productTypeClient;
    private final PurchaseClient purchaseClient;
    private final PositionRepository positionRepository;
    private final EmployeeProductTypeRepository employeeProductTypeRepository;

    public EmployeeResponse toEmployeeResponse(Employee employee) {

        List<ProductTypeResponse> productTypeResponses = productTypeClient
                .getEmployeeProductTypes(employee.getId()).getBody();

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
                        positionMapper.toPositionResponse(
                                positionRepository.findById(
                                            employee.getPositionId() == null ? 0 : employee.getPositionId()
                                        )
                                        .orElseGet(Position::new)
                        )
                ).storeResponse(
                        storeClient.getStoreById(employee.getStoreId()).getBody()
                ).productTypeResponses(
                        productTypeResponses == null || productTypeResponses.isEmpty() ?
                                null : productTypeResponses.stream().distinct().toList()
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
                        positionMapper.toPositionResponse(
                                positionRepository.findById(
                                            employee.getPositionId() == null ? 0 : employee.getPositionId()
                                        )
                                        .orElseGet(Position::new)
                        )
                )
                .build();
    }

    public Employee toEmployee(EmployeeCsv employeeCsv) {
        return Employee.builder()
                .surname(employeeCsv.getSurname())
                .name(employeeCsv.getName())
                .patronymic(employeeCsv.getPatronymic())
                .positionId(employeeCsv.getPositionId())
                .storeId(employeeCsv.getShopId())
                .birthDate(employeeCsv.getBirthDate())
                .gender(
                        employeeCsv.getGender() ? Gender.MALE : Gender.FEMALE
                )
                .build();
    }

    public Employee toEmployee(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .name(employeeRequest.getName())
                .surname(employeeRequest.getSurname())
                .patronymic(employeeRequest.getPatronymic())
                .birthDate(employeeRequest.getBirthDate())
                .gender(employeeRequest.getGender() != 0 ? Gender.MALE : Gender.FEMALE)
                .positionId(employeeRequest.getPositionId())
                .storeId(employeeRequest.getStoreId())
                .build();
    }

    public EmployeeRequest toEmployeeRequest(Employee employee) {
        return EmployeeRequest.builder()
                .id(employee.getId())
                .surname(employee.getSurname())
                .name(employee.getName())
                .patronymic(employee.getPatronymic())
                .storeId(employee.getStoreId())
                .positionId(employee.getPositionId())
                .birthDate(employee.getBirthDate())
                .gender(
                        employee.getGender() != Gender.FEMALE ? 1 : 0
                ).productTypeIds(
                        employeeProductTypeRepository.findEmployeeProductTypesIdsByEmployeeId(employee.getId())
                                .toArray(Long[]::new)
                )
                .build();
    }
}
