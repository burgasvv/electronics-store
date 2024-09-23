package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.ProductTypeResponse;
import org.burgas.productservice.service.ProductTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping
    public String getAllProductTypes(Model model) {
        return getProductTypePage(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getProductTypePage(
            @PathVariable int page, Model model
    ) {
        Page<ProductTypeResponse> allProductTypePages = productTypeService.findAllProductTypePages(page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, allProductTypePages.getTotalPages()).boxed().toList()
        );
        model.addAttribute(
                "productTypes", allProductTypePages.getContent()
        );
        return "productTypes/productTypes";
    }

    @GetMapping("/{product-type-id}")
    public String getProductType(
            @PathVariable(name = "product-type-id") Long typeId, Model model
    ) {
        model.addAttribute("productType", productTypeService.findById(typeId));
        return "productTypes/productType";
    }
}
