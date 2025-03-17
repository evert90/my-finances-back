package br.com.erp.bean.asset;

import br.com.erp.bean.tag.Tag;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record AssetReadonly (
    Long id,
    String name,
    String details,
    BigDecimal initialValue,
    BigDecimal endValue,
    LocalDate initialDate,
    LocalDate endDate,
    LocalDate redemptionDate,
    AssetType type,
    AssetRendaFixaType rendaFixaType,
    AssetRendaFixaRateType rendaFixaRateType,
    String bank,
    BigDecimal rate,
    Boolean liquidez,
    Set<Tag> tags,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    @Builder
    public AssetReadonly {}
}
