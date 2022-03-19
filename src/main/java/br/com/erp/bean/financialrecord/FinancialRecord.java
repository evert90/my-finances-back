package br.com.erp.bean.financialrecord;

import br.com.erp.bean.tag.Tag;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FinancialRecord(
        Long id,
        String name,
        String details,
        BigDecimal value,
        LocalDate date,
        FinancialRecordType type,
        List<Tag> tags,
        Boolean paid
) { }
