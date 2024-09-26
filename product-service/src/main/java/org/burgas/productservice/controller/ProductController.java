package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.model.response.ProductResponse;
import org.burgas.productservice.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        return getProductsPages(1, model);
    }

    @GetMapping("/pages/{page}")
    public String getProductsPages(
            @PathVariable int page, Model model
    ) {
        Page<ProductResponse> productPages = productService.findAllPages(page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, productPages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("products", productPages.getContent());
        return "products/products";
    }

    @GetMapping("/product-type/{product-type-id}")
    public String getProductsByProductType(
            @PathVariable(name = "product-type-id") Long productTypeId, Model model
    ) {
        model.addAttribute("productTypeId", productTypeId);
        return getProductsByProductTypePages(productTypeId, 1, model);
    }

    @GetMapping("/product-type-products/{product-type-id}/pages/{page}")
    public String getProductsByProductTypePages(
            @PathVariable(name = "product-type-id") Long productTypeId,
            @PathVariable int page, Model model
    ) {
        Page<ProductResponse> productsPages = productService.findPagesByProductTypeId(productTypeId, page, 20);
        model.addAttribute(
                "pages", IntStream.rangeClosed(1, productsPages.getTotalPages()).boxed().toList()
        );
        model.addAttribute("products", productsPages.getContent());
        return "productTypes/productTypeProducts";
    }

    @GetMapping("/{product-id}")
    public String getProductById(
            @PathVariable(name = "product-id") Long productId, Model model
    ) {
        model.addAttribute("product", productService.findById(productId));
        return "products/product";
    }

    @GetMapping("/rest-products/{product-id}")
    @ResponseBody
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable(name = "product-id") Long productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @PostMapping("/save-from-csv")
    public String saveDataFromCsvFile(@RequestPart MultipartFile file) throws IOException {
        productService.saveDataFromCsvFile(file);
        return "redirect:http://localhost:8765/products";
    }
}
