package br.com.erp.converter.financialrecord;

import br.com.erp.api.financialrecord.FinancialRecord;
import br.com.erp.api.Tag;
import br.com.erp.entity.FinancialRecordEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
public class FinancialRecordEntityToFinanacialRecord implements Function<FinancialRecordEntity, FinancialRecord> {
    @Override
    public FinancialRecord apply(FinancialRecordEntity entity) {
        return new FinancialRecord(
                entity.getId(),
                entity.getName(),
                entity.getDetails(),
                entity.getValue(),
                entity.getDate(),
                entity.getType(),
                ofNullable(entity.getTags())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(it -> new Tag(it.getId(), it.getName()))
                        .collect(toList())
        );
    }
}
