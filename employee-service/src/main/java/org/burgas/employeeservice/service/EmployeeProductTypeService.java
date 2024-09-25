package org.burgas.employeeservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.mapper.EmployeeProductTypeMapper;
import org.burgas.employeeservice.model.csv.EmployeeProductTypeCsv;
import org.burgas.employeeservice.repository.EmployeeProductTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class EmployeeProductTypeService {

    private final EmployeeProductTypeRepository employeeProductTypeRepository;
    private final EmployeeProductTypeMapper employeeProductTypeMapper;

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void saveDataFromCsv(MultipartFile multipartFile) throws IOException {
        employeeProductTypeRepository.saveAll(
                new CsvToBeanBuilder<EmployeeProductTypeCsv>(
                        new InputStreamReader(multipartFile.getInputStream())
                )
                        .withType(EmployeeProductTypeCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(employeeProductTypeMapper::toEmployeeProductType)
                        .toList()
        );
    }
}
