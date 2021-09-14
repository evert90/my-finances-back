package br.com.erp.converter.product;

import br.com.erp.api.product.ProductCategory;
import br.com.erp.entity.product.ProductCategoryEntity;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ProductCategoryToProductCategoryEntity implements Function<ProductCategory, ProductCategoryEntity> {

    private final UserService userService;

    @Override
    public ProductCategoryEntity apply(ProductCategory productCategory) {
        return new ProductCategoryEntity(
                productCategory.id(),
                productCategory.name(),
                userService.getCurrentUser());
    }
}
