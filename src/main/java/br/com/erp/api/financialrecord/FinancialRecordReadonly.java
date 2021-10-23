package br.com.erp.api.financialrecord;

import br.com.erp.api.Tag;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record FinancialRecordReadonly(
        Long id,
        String name,
        String details,
        BigDecimal value,
        LocalDate date,
        FinancialRecordType type,
        List<Tag> tags,
        Boolean paid,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }