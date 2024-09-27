package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.model.request.PositionRequest;
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
        return "redirect:http://localhost:8765/positions";
    }

    @GetMapping("/add-position-page")
    public String addPositionPage(Model model) {
        model.addAttribute("newPosition", new PositionRequest());
        return "positions/addPosition";
    }

    @PostMapping("/add-position")
    public String addPosition(@ModelAttribute PositionRequest positionRequest) {
        return "redirect:http://localhost:8765/positions/"
               + positionService.addPosition(positionRequest).getId();
    }

    @GetMapping("/edit-position-page/{position-id}")
    public String editPositionPage(
            @PathVariable(name = "position-id") Long positionId, Model model
    ) {
        model.addAttribute("position", positionService.findPositionRequestById(positionId));
        return "positions/editPosition";
    }

    @PostMapping("/edit-position")
    public String editPosition(@ModelAttribute PositionRequest positionRequest) {
        return "redirect:http://localhost:8765/positions/"
               + positionService.editPosition(positionRequest).getId();
    }

    @PostMapping("/delete-position/{position-id}")
    public String deletePosition(
            @PathVariable(name = "position-id") Long positionId
    ) {
        positionService.deletePosition(positionId);
        return "redirect:http://localhost:8765/positions";
    }
}
