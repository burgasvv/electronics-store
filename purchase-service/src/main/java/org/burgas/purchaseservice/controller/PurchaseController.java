package org.burgas.purchaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.model.response.PurchaseResponse;
import org.burgas.purchaseservice.service.PurchaseService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public String getAllPurchases(Model model) {
        return getPurchasePage(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getPurchasePage(
            @PathVariable int page, Model model
    ) {
        Page<PurchaseResponse> allPurchasePages = purchaseService.getAllPurchasePages(page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, allPurchasePages.getTotalPages()).boxed().toList()
        );
        model.addAttribute(
                "purchases", allPurchasePages.getContent()
        );
        return "purchases/purchases";
    }

    @GetMapping("/sort-purchases-by-date")
    public String getSortedPurchases(
            @RequestParam(name = "sort") String sort, Model model
    ) {
        return getSortedPurchasePage(1, sort, model);
    }

    @GetMapping("/sorted-purchases/{sort}/pages/{page}")
    public String getSortedPurchasePage(
            @PathVariable int page,
            @PathVariable(name = "sort") String sort,
            Model model
    ) {
        Page<PurchaseResponse> purchaseResponses = purchaseService.sortPurchasesByDate(sort, page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, purchaseResponses.getTotalPages()).boxed().toList()
        );
        model.addAttribute(
                "purchases", purchaseResponses.getContent()
        );
        model.addAttribute("sort", sort);
        return "purchases/sortedPurchases";
    }

    @GetMapping("/{purchase-id}")
    public String getPurchaseById(
            @PathVariable(name = "purchase-id") Long purchaseId, Model model
    ) {
        model.addAttribute("purchase", purchaseService.findById(purchaseId));
        return "purchases/purchase";
    }

    @GetMapping("/purchase/{purchase-id}")
    @ResponseBody
    public ResponseEntity<PurchaseResponse> getPurchase(
            @PathVariable(name = "purchase-id") Long purchaseId
    ) {
        return ResponseEntity.ok(
                purchaseService.findById(purchaseId)
        );
    }

    @PostMapping("/save-from-csv")
    public String saveDataFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        purchaseService.saveDataFromCsvFile(file);
        return "redirect:/purchases";
    }
}
