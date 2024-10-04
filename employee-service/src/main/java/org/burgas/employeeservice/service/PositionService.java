package org.burgas.employeeservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.entity.Position;
import org.burgas.employeeservice.exception.PositionNotFoundException;
import org.burgas.employeeservice.mapper.PositionMapper;
import org.burgas.employeeservice.model.csv.PositionCsv;
import org.burgas.employeeservice.model.request.PositionRequest;
import org.burgas.employeeservice.model.response.PositionResponse;
import org.burgas.employeeservice.repository.PositionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    public List<PositionResponse> findAll() {
        return positionRepository.findAll()
                .stream().map(positionMapper::toPositionResponse)
                .toList();
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
    public PositionRequest findPositionRequestById(Long positionId) {
        return positionRepository.findById(positionId)
                .map(positionMapper::toPositionRequest)
                .orElseThrow(
                        () -> new PositionNotFoundException(
                                "Должность с идентификатором " + positionId + " не найдена"
                        )
                );
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

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PositionResponse addPosition(PositionRequest positionRequest) {
        return positionMapper.toPositionResponse(
                positionRepository.save(
                        Position.builder()
                                .name(positionRequest.getName())
                                .build()
                )
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PositionResponse editPosition(PositionRequest positionRequest) {
        return positionMapper.toPositionResponse(
                positionRepository.save(
                        Position.builder()
                                .id(positionRequest.getId())
                                .name(positionRequest.getName())
                                .build()
                )
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public void deletePosition(Long positionId) {
        positionRepository.deleteById(positionId);
        positionRepository.updateEmployeesByPositionId(positionId);
    }
}
