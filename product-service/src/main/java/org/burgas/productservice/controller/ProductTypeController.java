package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.service.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping
    public String getAllProductTypes(Model model) {
        model.addAttribute("productTypes", productTypeService.findAll());
        return "types/types";
    }

    @GetMapping("/{type-id}")
    public String getProductType(
            @PathVariable(name = "type-id") Long typeId, Model model
    ) {
        model.addAttribute("productType", productTypeService.findById(typeId));
        return "types/type";
    }
}
