package br.com.erp.service.product;

import br.com.erp.bean.product.Product;
import br.com.erp.converter.product.ProductEntityToProduct;
import br.com.erp.converter.product.ProductToProductEntity;
import br.com.erp.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    private final ProductToProductEntity toEntity;

    private final ProductEntityToProduct toApi;

    public Product save(Product product) {
        return ofNullable(product)
                .map(it -> repository.save(toEntity.apply(it)))
                .map(toApi)
                .orElseThrow(() -> new RuntimeException("Erro ao salvar/retornar produto"));
    }

    public Set<Product> getAll() {
        return repository.findAll()
                .stream()
                .map(toApi)
                .collect(toSet());
    }
}
