package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.request.ProductTypeRequest;
import org.burgas.productservice.model.response.ProductTypeResponse;
import org.burgas.productservice.service.ProductTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/save-from-csv")
    public String saveDataFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        productTypeService.saveDataFromCsvFile(file);
        return "redirect:http://localhost:8765/productTypes";
    }

    @GetMapping("/add-product-type-page")
    public String addProductTypePage(Model model) {
        model.addAttribute("newProductType", new ProductTypeRequest());
        return "productTypes/addProductType";
    }

    @PostMapping("/add-product-type")
    public String addProductType(@ModelAttribute ProductTypeRequest productTypeRequest) {
        return "redirect:http://localhost:8765/product-types/"
               + productTypeService.addProductType(productTypeRequest).getId();
    }

    @GetMapping("/edit-product-type-page/{product-type-id}")
    public String editProductTypePage(
            @PathVariable(name = "product-type-id") Long productTypeId, Model model
    ) {
        model.addAttribute(
                "productType", productTypeService.findProductTypeRequestById(productTypeId)
        );
        return "productTypes/editProductType";
    }

    @PostMapping("/edit-product-type")
    public String editProductType(@ModelAttribute ProductTypeRequest productTypeRequest) {
        return "redirect:http://localhost:8765/product-types/"
               + productTypeService.editProductType(productTypeRequest).getId();
    }

    @PostMapping("/delete-product-type/{product-type-id}")
    public String deleteProductType(@PathVariable(name = "product-type-id") Long productTypeId) {
        productTypeService.deleteProductType(productTypeId);
        return "redirect:http://localhost:8765/product-types";
    }
}
