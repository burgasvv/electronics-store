package org.burgas.employeeservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.entity.Employee;
import org.burgas.employeeservice.entity.EmployeeProductType;
import org.burgas.employeeservice.mapper.EmployeeMapper;
import org.burgas.employeeservice.model.csv.EmployeeCsv;
import org.burgas.employeeservice.model.request.EmployeeRequest;
import org.burgas.employeeservice.model.response.*;
import org.burgas.employeeservice.repository.EmployeeProductTypeRepository;
import org.burgas.employeeservice.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeProductTypeRepository employeeProductTypeRepository;
    private final EmployeeMapper employeeMapper;

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<EmployeeResponse> findAllPages(int page, int size) {
        return employeeRepository.findAll(getPageRequest(page, size))
                .map(employeeMapper::toEmployeeResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public EmployeeResponse findById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toEmployeeResponse)
                .orElseGet(EmployeeResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<EmployeeResponse> findPagesByStoreId(Long storeId, int page, int size) {
        return employeeRepository.findEmployeesByStoreId(
                    storeId, getPageRequest(page, size)
                )
                .map(employeeMapper::toEmployeeResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<EmployeeResponse> findByStoreId(Long storeId) {
        return employeeRepository.findEmployeesByStoreId(storeId)
                .stream().map(employeeMapper::toEmployeeResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<EmployeeResponse> findPagesByPositionId(Long positionId, int page, int size) {
        return employeeRepository.findEmployeesByPositionId(
                positionId, getPageRequest(page, size)
        )
                .map(employeeMapper::toEmployeeResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchaseEmployeeResponse findByPurchaseId(Long purchaseId) {
        return employeeRepository.findEmployeeByPurchaseId(purchaseId)
                .map(employeeMapper::toPurchaseEmployeeResponse)
                .orElseGet(PurchaseEmployeeResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void saveFromCsvFile(MultipartFile multipartFile) throws IOException {
        employeeRepository.saveAll(
                new CsvToBeanBuilder<EmployeeCsv>(
                        new InputStreamReader(
                                multipartFile.getInputStream()
                        )
                )
                        .withType(EmployeeCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(employeeMapper::toEmployee)
                        .toList()
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<BestEmployeeResponse> findBestEmployees() {
        List<EmployeeResponse> employeeResponses = employeeRepository.findAll()
                .stream().map(employeeMapper::toEmployeeResponse)
                        .sorted(
                                Comparator.comparingLong(EmployeeResponse::getId)
                        ).filter(
                                employeeResponse -> !employeeResponse.getPurchaseResponses().isEmpty()
                ).toList();

        ArrayList<BestEmployeeResponse> bestEmployeeResponses = new ArrayList<>();

        employeeResponses.forEach(
                employeeResponse -> {

                    List<PurchaseResponse> purchaseResponses = employeeResponse.getPurchaseResponses()
                            .stream().filter(
                                    purchaseResponse -> purchaseResponse.getDateTime().contains("2024")
                            ).toList();

                    int purchasesAmount = purchaseResponses.size();
                    Integer fullPrice = purchaseResponses
                            .stream()
                            .map(
                                    purchaseResponse -> purchaseResponse.getPurchaseProductResponse()
                                            .getPrice()
                            )
                            .reduce(Integer::sum)
                            .orElse(null);

                    bestEmployeeResponses.add(
                            BestEmployeeResponse.builder()
                                    .id(employeeResponse.getId())
                                    .employeeResponse(employeeResponse)
                                    .purchasesAmount(purchasesAmount)
                                    .fullPrice(fullPrice)
                                    .build()
                    );
                }
        );

        return bestEmployeeResponses.stream()
                .sorted(
                        Comparator.comparing(
                                bestEmployeeResponse -> bestEmployeeResponse.getEmployeeResponse()
                                        .getPositionResponse().getId()
                        )
                ).toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<BestJuniorConsultantResponse> findBestJuniorConsultant() {
        List<EmployeeResponse> employeeResponses = employeeRepository.findAll()
                .stream().map(employeeMapper::toEmployeeResponse)
                .filter(
                        employeeResponse -> employeeResponse.getPositionResponse()
                                .getName().equalsIgnoreCase("Младший продавец-консультант")
                ).toList();

        ArrayList<BestJuniorConsultantResponse> bestJuniorConsultantResponses = new ArrayList<>();

        employeeResponses.forEach(
                employeeResponse -> {

                    List<PurchaseResponse> smartWatchesPurchases = employeeResponse.getPurchaseResponses()
                            .stream().filter(
                                    purchaseResponse -> purchaseResponse.getPurchaseProductResponse()
                                            .getProductTypeResponse().getName().equalsIgnoreCase("Умные часы")
                            ).toList();

                    if (!smartWatchesPurchases.isEmpty()) {
                        bestJuniorConsultantResponses.add(
                                BestJuniorConsultantResponse.builder()
                                        .id(employeeResponse.getId())
                                        .employeeResponse(employeeResponse)
                                        .smartWatchesAmount(smartWatchesPurchases.size())
                                        .build()
                        );
                    }
                }
        );

        Integer maxPurchases = bestJuniorConsultantResponses.stream()
                .map(BestJuniorConsultantResponse::getSmartWatchesAmount)
                .max(
                        Comparator.comparingInt(Integer::intValue)
                ).orElse(null);

        return bestJuniorConsultantResponses
                .stream().filter(
                        bestJuniorConsultantResponse -> bestJuniorConsultantResponse
                                .getSmartWatchesAmount().equals(maxPurchases)
                ).toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.save(employeeMapper.toEmployee(employeeRequest));
        Arrays.stream(employeeRequest.getProductTypeIds())
                .forEach(
                        aLong -> employeeProductTypeRepository.save(
                                EmployeeProductType.builder()
                                        .employeeId(employee.getId())
                                        .productTypeId(aLong)
                                        .build()
                        )
                );
        return employeeMapper.toEmployeeResponse(employee);
    }
}
