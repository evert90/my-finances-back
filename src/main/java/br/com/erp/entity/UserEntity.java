package br.com.erp.entity;

import br.com.erp.api.Role;
import br.com.erp.entity.product.ProductCategoryEntity;
import br.com.erp.entity.product.ProductEntity;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Entity
public class UserEntity {

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = TABLE, generator = "userGenerator")
    @TableGenerator(name = "userGenerator", table = "hibernate_sequences")
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductCategoryEntity> productCategories;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> products;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FinancialRecordEntity> financialRecords;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TagEntity> tags;

}
