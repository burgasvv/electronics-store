package org.burgas.purchaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.purchaseservice.service.PurchaseTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/purchase-types")
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping
    public String getAllPurchaseTypes(Model model) {
        model.addAttribute("purchaseTypes", purchaseTypeService.findAll());
        return "purchaseTypes/purchaseTypes";
    }

    @GetMapping("/{purchase-type-id}")
    public String getPurchaseType(
            @PathVariable(name = "purchase-type-id") Long purchaseTypeId, Model model
    ) {
        model.addAttribute("purchaseType", purchaseTypeService.findById(purchaseTypeId));
        return "purchaseTypes/purchaseType";
    }
}
