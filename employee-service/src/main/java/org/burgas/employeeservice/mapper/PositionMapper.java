package org.burgas.employeeservice.mapper;

import org.burgas.employeeservice.entity.Position;
import org.burgas.employeeservice.model.csv.PositionCsv;
import org.burgas.employeeservice.model.request.PositionRequest;
import org.burgas.employeeservice.model.response.PositionResponse;
import org.springframework.stereotype.Service;

@Service
public class PositionMapper {

    public PositionResponse toPositionResponse(Position position) {
        return PositionResponse.builder()
                .id(position.getId())
                .name(position.getName())
                .build();
    }

    public Position toPosition(PositionCsv positionCsv) {
        return Position.builder()
                .name(positionCsv.getName())
                .build();
    }

    public PositionRequest toPositionRequest(Position position) {
        return PositionRequest.builder()
                .id(position.getId())
                .name(position.getName())
                .build();
    }
}
