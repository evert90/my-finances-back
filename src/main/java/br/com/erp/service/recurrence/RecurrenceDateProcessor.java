package br.com.erp.service.recurrence;

import br.com.erp.bean.recurrence.RecurrencePeriod;
import br.com.erp.entity.FinancialRecordRecurrenceEntity;

import java.time.LocalDate;

public interface RecurrenceDateProcessor {
    Boolean matches(RecurrencePeriod recurrencePeriod);
    LocalDate getNext(FinancialRecordRecurrenceEntity entity);
}
