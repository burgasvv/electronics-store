package org.burgas.storeservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.storeservice.model.request.StoreRequest;
import org.burgas.storeservice.model.response.StoreResponse;
import org.burgas.storeservice.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public String getAllStores(Model model) {
        return getStorePage(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getStorePage(
            @PathVariable int page, Model model
    ) {
        Page<StoreResponse> allStorePages = storeService.findAllStorePages(page, 10);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, allStorePages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("stores", allStorePages.getContent());
        return "stores";
    }

    @GetMapping("/{store-id}")
    public String getStoreById(
            @PathVariable(name = "store-id") Long storeId, Model model
    ) {
        model.addAttribute("store", storeService.findById(storeId));
        return "store";
    }

    @GetMapping("/store-money-purchase-type")
    public String getStoreMoneyByPurchaseType(
            @RequestParam(name = "store-id") Long storeId,
            @RequestParam(name = "purchase-type") String purchaseType,
            Model model
    ) {
        model.addAttribute(
                "storeMoney", storeService.findSumPriceInStoreByPurchaseType(storeId, purchaseType)
        );
        return "storeMoney";
    }

    @PostMapping("/save-from-csv")
    public String saveDateFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        storeService.saveDataFromCsvFile(file);
        return "redirect:http://localhost:8765/stores";
    }

    @GetMapping("/add-store-page")
    public String addStorePage(Model model) {
        model.addAttribute("newStore", new StoreRequest());
        return "addStore";
    }

    @PostMapping("/add-store")
    public String addStore(@ModelAttribute StoreRequest storeRequest) {
        return "redirect:http://localhost:8765/stores/"
               + storeService.addStore(storeRequest).getId();
    }

    @GetMapping("/edit-store-page/{store-id}")
    public String editStorePage(
            @PathVariable(name = "store-id") Long storeId, Model model
    ) {
        model.addAttribute("store", storeService.findStoreRequestById(storeId));
        return "editStore";
    }

    @PostMapping("/edit-store")
    public String editStore(@ModelAttribute StoreRequest storeRequest) {
        return "redirect:http://localhost:8765/stores/"
               + storeService.editStore(storeRequest).getId();
    }

    @PostMapping("/delete-store/{store-id}")
    public String deleteStore(
            @PathVariable(name = "store-id") Long storeId
    ) {
        storeService.deleteStore(storeId);
        return "redirect:http://localhost:8765/stores";
    }
}
