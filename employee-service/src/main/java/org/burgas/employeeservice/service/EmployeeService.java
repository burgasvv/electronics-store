package org.burgas.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.exception.EmployeeNotFoundException;
import org.burgas.employeeservice.mapper.EmployeeMapper;
import org.burgas.employeeservice.model.*;
import org.burgas.employeeservice.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
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
                .orElseThrow(
                        () -> new EmployeeNotFoundException(
                                "Не найден сотрудник с идентификатором: " + id
                        )
                );
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
    public PurchaseEmployeeResponse findByPurchaseId(Long purchaseId) {
        return employeeRepository.findEmployeeByPurchaseId(purchaseId)
                .map(employeeMapper::toPurchaseEmployeeResponse)
                .orElseThrow(
                        () -> new EmployeeNotFoundException(
                                "Не найден сотрудник с по идентификатору покупки: " + purchaseId
                        )
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
                                Comparator.comparingLong(EmployeeResponse::id)
                        ).filter(
                                employeeResponse -> !employeeResponse.purchaseResponses().isEmpty()
                ).toList();

        ArrayList<BestEmployeeResponse> bestEmployeeResponses = new ArrayList<>();

        employeeResponses.forEach(
                employeeResponse -> {

                    List<PurchaseResponse> purchaseResponses = employeeResponse.purchaseResponses()
                            .stream().filter(
                                    purchaseResponse -> purchaseResponse.dateTime().contains("2024")
                            ).toList();

                    int purchasesAmount = purchaseResponses.size();
                    Integer fullPrice = purchaseResponses
                            .stream()
                            .map(
                                    purchaseResponse -> purchaseResponse.purchaseProductResponse()
                                            .price()
                            )
                            .reduce(Integer::sum)
                            .orElse(null);

                    bestEmployeeResponses.add(
                            BestEmployeeResponse.builder()
                                    .id(employeeResponse.id())
                                    .employeeResponse(employeeResponse)
                                    .purchasesAmount(purchasesAmount)
                                    .fullPrice(fullPrice)
                                    .build()
                    );
                }
        );

        return bestEmployeeResponses;
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public List<BestJuniorConsultantResponse> findBestJuniorConsultant() {
        List<EmployeeResponse> employeeResponses = employeeRepository.findAll()
                .stream().map(employeeMapper::toEmployeeResponse)
                .filter(
                        employeeResponse -> employeeResponse.positionResponse()
                                .name().equalsIgnoreCase("Младший продавец-консультант")
                ).toList();

        ArrayList<BestJuniorConsultantResponse> bestJuniorConsultantResponses = new ArrayList<>();

        employeeResponses.forEach(
                employeeResponse -> {

                    List<PurchaseResponse> smartWatchesPurchases = employeeResponse.purchaseResponses()
                            .stream().filter(
                                    purchaseResponse -> purchaseResponse.purchaseProductResponse()
                                            .productTypeResponse().name().equalsIgnoreCase("Умные часы")
                            ).toList();

                    if (!smartWatchesPurchases.isEmpty()) {
                        bestJuniorConsultantResponses.add(
                                BestJuniorConsultantResponse.builder()
                                        .id(employeeResponse.id())
                                        .employeeResponse(employeeResponse)
                                        .smartWatchesAmount(smartWatchesPurchases.size())
                                        .build()
                        );
                    }
                }
        );

        Integer maxPurchases = bestJuniorConsultantResponses.stream()
                .map(BestJuniorConsultantResponse::smartWatchesAmount)
                .max(
                        Comparator.comparingInt(Integer::intValue)
                ).orElse(null);

        return bestJuniorConsultantResponses
                .stream().filter(
                        bestJuniorConsultantResponse -> bestJuniorConsultantResponse
                                .smartWatchesAmount().equals(maxPurchases)
                ).toList();
    }
}
