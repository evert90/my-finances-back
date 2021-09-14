package br.com.erp.converter.financialrecord;

import br.com.erp.api.FinancialRecord;
import br.com.erp.entity.FinancialRecordEntity;
import br.com.erp.entity.TagEntity;
import br.com.erp.repository.TagRepository;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class FinancialRecordToFinancialRecordEntity implements Function<FinancialRecord, FinancialRecordEntity> {

    private final TagRepository tagRepository;

    private final UserService userService;

    @Override
    public FinancialRecordEntity apply(FinancialRecord financialRecord) {
        var user =  userService.getCurrentUser();
        return new FinancialRecordEntity(
                financialRecord.id(),
                financialRecord.name(),
                financialRecord.details(),
                financialRecord.value(),
                financialRecord.type(),
                financialRecord.date(),
                financialRecord.tags()
                        .stream()
                        .map(it -> tagRepository.findById(it.id()).orElse(tagRepository.save(new TagEntity(it.id(), it.name(), user))))
                        .collect(toSet()),
                user
        );
    }

}
