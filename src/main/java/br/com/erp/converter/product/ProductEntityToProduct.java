package br.com.erp.converter.product;

import br.com.erp.api.product.Product;
import br.com.erp.entity.product.ProductCategoryEntity;
import br.com.erp.entity.product.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

@Service
public class ProductEntityToProduct implements Function<ProductEntity, Product> {
    @Override
    public Product apply(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCategories().stream().map(ProductCategoryEntity::getId).collect(toSet()),
                productEntity.getDetails()
        );
    }
}
