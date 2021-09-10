package br.com.erp.service.product;

import br.com.erp.api.product.ProductCategory;
import br.com.erp.converter.product.ProductCategoryEntityToProductCategory;
import br.com.erp.converter.product.ProductCategoryToProductCategoryEntity;
import br.com.erp.repository.product.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class ProductCategoryService {

    private final ProductCategoryToProductCategoryEntity toEntity;

    private final ProductCategoryEntityToProductCategory toApi;

    private final ProductCategoryRepository repository;

    public ProductCategory save(ProductCategory category) {
        return ofNullable(category)
                .map(it -> repository.save(toEntity.apply(it)))
                .map(toApi)
                .orElseThrow(() -> new RuntimeException("Erro ao salvar/retornar categoria"));
    }

    public Set<ProductCategory> getAll() {
        return repository.findAll()
                .stream()
                .map(toApi)
                .collect(toSet());
    }

}
