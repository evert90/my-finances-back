package br.com.erp.entity;

import br.com.erp.api.asset.AssetType;
import br.com.erp.api.asset.AssetRendaFixaRateType;
import br.com.erp.api.asset.AssetRendaFixaType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.TABLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asset")
@Entity
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = TABLE, generator = "financialRecordGenerator")
    @TableGenerator(name = "financialRecordGenerator", table = "hibernate_sequences")
    private Long id;

    @Column
    private String name;

    @Column
    private String details;

    @Column
    private BigDecimal initialValue;

    @Column
    private BigDecimal endValue;

    @Column
    private LocalDate initialDate;

    @Column
    private LocalDate endDate;

    @Column
    @Enumerated(EnumType.STRING)
    private AssetType type;

    @Column
    @Enumerated(EnumType.STRING)
    private AssetRendaFixaType rendaFixaType;

    @Column
    @Enumerated(EnumType.STRING)
    private AssetRendaFixaRateType rendaFixaRateType;

    @Column
    private String bank;

    @Column
    private BigDecimal rate;

    @Column
    private Boolean liquidez;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "asset_tags",
            joinColumns = {@JoinColumn(name = "id_asset")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")})
    @Column
    private List<TagEntity> tags;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
