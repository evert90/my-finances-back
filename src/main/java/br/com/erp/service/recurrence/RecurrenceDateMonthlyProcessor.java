package br.com.erp.service.recurrence;

import br.com.erp.bean.recurrence.RecurrencePeriod;
import br.com.erp.entity.FinancialRecordRecurrenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.LocalDate.*;

@RequiredArgsConstructor
@Service
public class RecurrenceDateMonthlyProcessor implements RecurrenceDateProcessor {

    private final Clock clock;

    @Override
    public Boolean matches(RecurrencePeriod recurrencePeriod) {
        return recurrencePeriod == RecurrencePeriod.MONTHLY;
    }

    @Override
    public LocalDate getNext(FinancialRecordRecurrenceEntity entity) {
        var now = now(clock);
        return of(now.getYear(), now.getMonth(), entity.getDate().getDayOfMonth())
                .plusMonths(entity.getPeriodQuantity());
    }
}
