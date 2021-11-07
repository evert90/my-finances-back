package br.com.erp.api.asset;

import br.com.erp.api.Tag;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AssetReadonly (
    Long id,
    String name,
    String details,
    BigDecimal initialValue,
    BigDecimal endValue,
    LocalDate initialDate,
    LocalDate endDate,
    AssetType type,
    AssetRendaFixaType rendaFixaType,
    AssetRendaFixaRateType rendaFixaRateType,
    String bank,
    BigDecimal rate,
    Boolean liquidez,
    List<Tag> tags,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
