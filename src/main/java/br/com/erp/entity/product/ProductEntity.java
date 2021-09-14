package br.com.erp.entity.product;

import br.com.erp.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.TABLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = TABLE, generator = "productGenerator")
    @TableGenerator(name = "productGenerator", table = "hibernate_sequences")
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_categories",
            joinColumns = {@JoinColumn(name = "id_product")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")})
    @Column
    private Set<ProductCategoryEntity> categories;

    @Column
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
