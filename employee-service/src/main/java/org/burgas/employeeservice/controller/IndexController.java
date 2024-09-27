package org.burgas.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.employeeservice.feign.PurchaseTypeClient;
import org.burgas.employeeservice.feign.StoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/index")
public class IndexController {

    private final StoreClient storeClient;
    private final PurchaseTypeClient purchaseTypeClient;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute(
                "stores", storeClient.getAllStores().getBody()
        );
        model.addAttribute(
                "purchaseTypes", purchaseTypeClient.getAllPurchaseTypes().getBody()
        );
        return "index";
    }
}
