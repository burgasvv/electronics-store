package org.burgas.purchaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.model.PurchaseResponse;
import org.burgas.purchaseservice.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public String getAllPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.findAll());
        return "purchases/purchases";
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
}
