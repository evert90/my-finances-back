package br.com.erp.bean.financialrecord;

import br.com.erp.bean.recurrence.RecurrencePeriod;
import br.com.erp.bean.tag.Tag;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Builder(toBuilder = true)
public record FinancialRecord(
        Long id,
        String name,
        String details,
        BigDecimal value,
        LocalDate date,
        FinancialRecordType type,
        Set<Tag> tags,
        Boolean paid,
        Boolean notification,
        boolean recurrence,
        RecurrencePeriod recurrencePeriod,
        Integer recurrenceQuantity,
        Boolean recurrenceEmptyValue
) { }
