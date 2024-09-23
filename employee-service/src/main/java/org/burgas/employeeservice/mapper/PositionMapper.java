package org.burgas.employeeservice.mapper;

import org.burgas.employeeservice.entity.Position;
import org.burgas.employeeservice.model.PositionResponse;
import org.springframework.stereotype.Service;

@Service
public class PositionMapper {

    public PositionResponse toPositionResponse(Position position) {
        return PositionResponse.builder()
                .id(position.getId())
                .name(position.getName())
                .build();
    }
}
