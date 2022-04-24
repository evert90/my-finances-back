package br.com.erp.service.recurrence;

import br.com.erp.entity.FinancialRecordRecurrenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecurrenceDateService {

    private final List<RecurrenceDateProcessor> recurrenceDateProcessors;

    public LocalDate getNext(FinancialRecordRecurrenceEntity entity) {
        return recurrenceDateProcessors
                .stream()
                .filter(it -> it.matches(entity.getPeriod()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Processador de recorrência não encontrado para id: " + entity.getId()))
                .getNext(entity);
    }
}
