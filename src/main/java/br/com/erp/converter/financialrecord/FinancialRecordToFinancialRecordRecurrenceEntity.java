package br.com.erp.converter.financialrecord;

import br.com.erp.bean.financialrecord.FinancialRecord;
import br.com.erp.bean.tag.Tag;
import br.com.erp.entity.FinancialRecordRecurrenceEntity;
import br.com.erp.entity.TagEntity;
import br.com.erp.entity.UserEntity;
import br.com.erp.repository.TagRepository;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Function;

import static br.com.erp.bean.financialrecord.FinancialRecordType.EXPENSE;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class FinancialRecordToFinancialRecordRecurrenceEntity
        implements Function<FinancialRecord, FinancialRecordRecurrenceEntity> {

    private final TagRepository tagRepository;        ;

    private final UserService userService;

    @Override
    public FinancialRecordRecurrenceEntity apply(FinancialRecord financialRecord) {
        var user = userService.getCurrentUser();

        return FinancialRecordRecurrenceEntity
                .builder()
                .name(financialRecord.name())
                .details(financialRecord.details())
                .value(financialRecord.value())
                .type(financialRecord.type())
                .date(financialRecord.date())
                .tags(ofNullable(financialRecord.tags())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(it -> tagRepository.findByUserAndName(user, it.name())
                                .orElseGet(() -> saveTag(it, user)))
                        .collect(toSet()))
                .user(user)
                .paid(financialRecord.paid())
                .notification(financialRecord.notification())
                .period(financialRecord.recurrencePeriod())
                .periodQuantity(financialRecord.recurrenceQuantity())
                .emptyValue(financialRecord.recurrenceEmptyValue())
                .createdAt(now())
                .updatedAt(now())
                .build();
    }

    private TagEntity saveTag(Tag tag, UserEntity user) {
        return tagRepository.save(new TagEntity(tag.id(), tag.name(), user));
    }
}
