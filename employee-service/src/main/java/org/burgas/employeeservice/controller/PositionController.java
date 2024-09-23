package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    public String getAllPositions(Model model) {
        model.addAttribute("positions", positionService.findAll());
        return "positions/positions";
    }

    @GetMapping("/{position-id}")
    public String getPositionById(
            @PathVariable(name = "position-id") Long positionId, Model model
    ) {
        model.addAttribute("position", positionService.findById(positionId));
        return "positions/position";
    }
}
