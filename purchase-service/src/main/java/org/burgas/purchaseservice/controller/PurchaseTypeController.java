package org.burgas.purchaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.model.request.PurchaseTypeRequest;
import org.burgas.purchaseservice.model.response.PurchaseTypeResponse;
import org.burgas.purchaseservice.service.PurchaseTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/purchase-types")
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping
    public String getAllPurchaseTypes(Model model) {
        return getPurchaseTypePage(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getPurchaseTypePage(
            @PathVariable int page, Model model
    ) {
        Page<PurchaseTypeResponse> allPurchaseTypePages = purchaseTypeService.getAllPurchaseTypePages(page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, allPurchaseTypePages.getTotalPages()).boxed().toList()
        );
        model.addAttribute(
                "purchaseTypes", allPurchaseTypePages.getContent()
        );
        return "purchaseTypes/purchaseTypes";
    }

    @GetMapping("/{purchase-type-id}")
    public String getPurchaseType(
            @PathVariable(name = "purchase-type-id") Long purchaseTypeId, Model model
    ) {
        model.addAttribute("purchaseType", purchaseTypeService.findById(purchaseTypeId));
        return "purchaseTypes/purchaseType";
    }

    @PostMapping("/save-from-csv")
    public String saveDataFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        purchaseTypeService.safeDataFromCsvFile(file);
        return "redirect:http://localhost:8765/purchase-types";
    }

    @GetMapping("/add-purchase-type-page")
    public String addPurchaseTypePage(Model model) {
        model.addAttribute("newPurchaseType", new PurchaseTypeRequest());
        return "purchaseTypes/addPurchaseType";
    }

    @PostMapping("/add-purchase-type")
    public String addPurchaseType(@ModelAttribute PurchaseTypeRequest purchaseTypeRequest) {
        return "redirect:http://localhost:8765/purchase-types/"
               + purchaseTypeService.addPurchaseType(purchaseTypeRequest).getId();
    }

    @GetMapping("/edit-purchase-type-page/{purchase-type-id}")
    public String editPurchaseTypePage(
            @PathVariable(name = "purchase-type-id") Long purchaseTypeId, Model model
    ) {
        model.addAttribute(
                "purchaseType", purchaseTypeService.findPurchaseTypeRequestById(purchaseTypeId)
        );
        return "purchaseTypes/editPurchaseType";
    }

    @PostMapping("/edit-purchase-type")
    public String editPurchaseType(@ModelAttribute PurchaseTypeRequest purchaseTypeRequest) {
        return "redirect:http://localhost:8765/purchase-types/"
               + purchaseTypeService.editPurchaseType(purchaseTypeRequest).getId();
    }

    @PostMapping("/delete-purchase-type/{purchase-type-id}")
    public String deletePurchaseType(
            @PathVariable(name = "purchase-type-id") Long purchaseTypeId
    ) {
        purchaseTypeService.deletePurchaseType(purchaseTypeId);
        return "redirect:http://localhost:8765/purchase-types";
    }
}
