package br.com.erp.converter.product;

import br.com.erp.bean.product.ProductCategory;
import br.com.erp.entity.product.ProductCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryEntityToProductCategory implements Function<ProductCategoryEntity, ProductCategory> {
    @Override
    public ProductCategory apply(ProductCategoryEntity category) {
        return new ProductCategory(
                category.getId(),
                category.getName()
        );
    }
}
