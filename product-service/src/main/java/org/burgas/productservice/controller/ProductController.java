package org.burgas.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.productservice.feign.StoreClient;
import org.burgas.productservice.model.request.ProductRequest;
import org.burgas.productservice.model.response.ProductResponse;
import org.burgas.productservice.service.ProductService;
import org.burgas.productservice.service.ProductTypeService;
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
    private final ProductTypeService productTypeService;
    private final StoreClient storeClient;

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

    @GetMapping("/add-product-page")
    public String addProductPage(Model model) {
        model.addAttribute("newProduct", new ProductRequest());
        model.addAttribute("stores", storeClient.getAllStores().getBody());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "products/addProduct";
    }

    @GetMapping("/add-product")
    public String addProduct(
            @ModelAttribute ProductRequest productRequest, Model model
    ) {
        model.addAttribute(
                "chosenStores", productService.getStoresByIds(productRequest.getStoreIds())
        );
        model.addAttribute("product", productRequest);
        return "products/addProductAmount";
    }

    @PostMapping("/add-products-amounts")
    public String addProductsAmounts(@ModelAttribute ProductRequest productRequest) {
        return "redirect:http://localhost:8765/products/"
               + productService.addProduct(productRequest).getId();
    }
}
