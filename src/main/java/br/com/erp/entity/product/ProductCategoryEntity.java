package br.com.erp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.TABLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_category", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Entity
public class ProductCategoryEntity {

    public ProductCategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = TABLE, generator = "productCategoryGenerator")
    @TableGenerator(name = "productCategoryGenerator", table = "hibernate_sequences")
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_categories",
            joinColumns = {@JoinColumn(name = "id_category")},
            inverseJoinColumns = {@JoinColumn(name = "id_product")})
    @Column
    private Set<ProductEntity> products;

}
