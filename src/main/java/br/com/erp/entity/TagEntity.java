package br.com.erp.entity;

import br.com.erp.entity.product.ProductEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.TABLE;

@Data
@NoArgsConstructor
@Table(name = "tag", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Entity
public class TagEntity {

    public TagEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = TABLE, generator = "tagGenerator")
    @TableGenerator(name = "tagGenerator", table = "hibernate_sequences")
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "financial_record_tags",
            joinColumns = {@JoinColumn(name = "id_tag")},
            inverseJoinColumns = {@JoinColumn(name = "id_financial_record")})
    @Column
    private Set<ProductEntity> products;
}