package br.com.erp.converter.product;

import br.com.erp.api.product.Product;
import br.com.erp.entity.product.ProductEntity;
import br.com.erp.repository.product.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class ProductToProductEntity implements Function<Product, ProductEntity> {

    private final ProductCategoryRepository categoryRepository;

    @Override
    public ProductEntity apply(Product product) {
        return new ProductEntity(
                product.id(),
                product.name(),
                product.categoriesIds()
                        .stream()
                        .map(it -> categoryRepository.findById(it)
                                .orElseThrow(() -> new RuntimeException("Categoria " + it + " não encontrada")))
                        .collect(toSet()),
                product.details()
        );

    }
}
