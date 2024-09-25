package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.model.response.PositionResponse;
import org.burgas.employeeservice.service.PositionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    public String getAllPositions(Model model) {
        return getPositionPage(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getPositionPage(
            @PathVariable int page, Model model
    ) {
        Page<PositionResponse> allPages = positionService.findAllPages(page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, allPages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("positions", allPages.getContent());
        return "positions/positions";
    }

    @GetMapping("/{position-id}")
    public String getPositionById(
            @PathVariable(name = "position-id") Long positionId, Model model
    ) {
        model.addAttribute("position", positionService.findById(positionId));
        return "positions/position";
    }

    @PostMapping("/save-from-csv")
    public String getDataFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        positionService.saveDataFromCsvFile(file);
        return "redirect:/positions";
    }
}
