package org.burgas.employeeservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.mapper.PositionMapper;
import org.burgas.employeeservice.model.csv.PositionCsv;
import org.burgas.employeeservice.model.response.PositionResponse;
import org.burgas.employeeservice.repository.PositionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    private PageRequest getPageRequest(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public Page<PositionResponse> findAllPages(int page, int size) {
        return positionRepository.findAll(getPageRequest(page, size))
                .map(positionMapper::toPositionResponse);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PositionResponse findById(Long id) {
        return positionRepository.findById(id)
                .map(positionMapper::toPositionResponse)
                .orElseGet(PositionResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void saveDataFromCsvFile(MultipartFile multipartFile) throws IOException {
        positionRepository.saveAll(
                new CsvToBeanBuilder<PositionCsv>(
                        new InputStreamReader(multipartFile.getInputStream())
                )
                        .withType(PositionCsv.class)
                        .withSeparator(';')
                        .build()
                        .parse()
                        .stream().map(positionMapper::toPosition)
                        .toList()
        );
    }
}
