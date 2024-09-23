package org.burgas.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.model.PositionResponse;
import org.burgas.employeeservice.exception.PositionNotFoundException;
import org.burgas.employeeservice.mapper.PositionMapper;
import org.burgas.employeeservice.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

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
                .orElseThrow(
                        () -> new PositionNotFoundException(
                                "Должность с идентификатором " + id + " не найдена"
                        )
                );
    }
}
