package br.com.erp.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record FinancialRecord(
        Long id,
        String name,
        String details,
        BigDecimal value,
        LocalDate date,
        FinancialRecordType type,
        Set<Tag> tags
) { }
