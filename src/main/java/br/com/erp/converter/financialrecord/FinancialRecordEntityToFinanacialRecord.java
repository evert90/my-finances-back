package br.com.erp.converter.financialrecord;

import br.com.erp.api.FinancialRecord;
import br.com.erp.api.Tag;
import br.com.erp.entity.FinancialRecordEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

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
                entity.getTags().stream().map(it -> new Tag(it.getId(), it.getName())).collect(toSet())
        );
    }
}
