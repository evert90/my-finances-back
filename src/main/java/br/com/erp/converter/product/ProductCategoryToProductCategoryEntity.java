package br.com.erp.converter.product;

import br.com.erp.api.product.ProductCategory;
import br.com.erp.entity.product.ProductCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryToProductCategoryEntity implements Function<ProductCategory, ProductCategoryEntity> {
    @Override
    public ProductCategoryEntity apply(ProductCategory productCategory) {
        return new ProductCategoryEntity(
                productCategory.id(),
                productCategory.name());
    }
}
