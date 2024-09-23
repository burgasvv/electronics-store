package org.burgas.storeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.model.StoreResponse;
import org.burgas.storeservice.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public String getAllStores(Model model) {
        model.addAttribute("stores", storeService.findAll());
        return "stores";
    }

    @GetMapping("/{store-id}")
    public String getStoreById(
            @PathVariable(name = "store-id") Long storeId, Model model
    ) {
        model.addAttribute("store", storeService.findById(storeId));
        return "store";
    }

    @GetMapping("/store-money-purchase-type/{store-id}/{purchase-type}")
    public String getStoreMoneyByPurchaseType(
            @PathVariable(name = "store-id") Long storeId,
            @PathVariable(name = "purchase-type") String purchaseType,
            Model model
    ) {
        model.addAttribute(
                "storeMoney", storeService.findSumPriceInStoreByPurchaseType(storeId, purchaseType)
        );
        return "storeMoney";
    }
}
