package br.com.erp.controller.product;

import br.com.erp.api.product.ProductCategory;
import br.com.erp.service.product.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products-category")
public class ProductCategoryController {

    private final ProductCategoryService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    ProductCategory save(@RequestBody ProductCategory category) {
        return service.save(category);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    Set<ProductCategory> getAll() {
        return service.getAll();
    }
}
