package br.com.erp.controller;

import br.com.erp.api.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.singletonList;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    List<Product> getAll() {
        return singletonList(new Product(1L, "Mangá Naruto - Edição 78"));
    }
}
