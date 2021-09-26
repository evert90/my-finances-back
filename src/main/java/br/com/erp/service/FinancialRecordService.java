package br.com.erp.service;

import br.com.erp.api.Tag;
import br.com.erp.api.TagTotal;
import br.com.erp.api.financialrecord.FinancialRecord;
import br.com.erp.api.financialrecord.FinancialRecordTotal;
import br.com.erp.api.financialrecord.FinancialRecordType;
import br.com.erp.converter.TagTotalRepositoryToTagTotal;
import br.com.erp.converter.financialrecord.FinancialRecordEntityToFinanacialRecord;
import br.com.erp.converter.financialrecord.FinancialRecordToFinancialRecordEntity;
import br.com.erp.repository.FinancialRecordRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class FinancialRecordService {
    private final FinancialRecordRepository repository;

    private final FinancialRecordToFinancialRecordEntity toEntity;

    private final FinancialRecordEntityToFinanacialRecord toApi;

    private final TagTotalRepositoryToTagTotal toTagTotal;

    private final UserService userService;

    public FinancialRecord save(FinancialRecord financialRecord) {
        return ofNullable(financialRecord)
                .map(it -> repository.save(toEntity.apply(it)))
                .map(toApi)
                .orElseThrow(() -> new RuntimeException("Erro ao salvar/retornar o registro financeiro"));
    }

    public Set<FinancialRecord> getByType(FinancialRecordType type) {
        return repository.findByType(type)
                .stream()
                .map(toApi)
                .collect(toSet());
    }

    public Set<FinancialRecord> getByPeriod(LocalDate start, LocalDate end) {
        return repository.findByUserAndDateBetweenOrderByDateDesc(userService.getCurrentUser(), start, end)
                .stream()
                .map(toApi)
                .collect(toCollection(LinkedHashSet::new));
    }

    public Set<FinancialRecordTotal> getTotal() {
        return repository.getTotalReport(userService.getCurrentUser());
    }

    public Set<FinancialRecordTotal> getTotalByPeriod(LocalDate start, LocalDate end) {
        return repository.getTotalReportByPeriod(start, end, userService.getCurrentUser());
    }

    public Set<TagTotal> getTotalByPeriodAndTags(LocalDate start, LocalDate end, Set<Long> tagIds) {
        return repository.getTotalReportByPeriodAndTagIds(
                    start,
                    end,
                    userService.getCurrentUser().getId(),
                    tagIds
                )
                .stream()
                .map(toTagTotal)
                .collect(toSet());
    }

    public Set<FinancialRecord> getAll() {
        return repository.findByUserOrderByDateDesc(userService.getCurrentUser())
                .stream()
                .map(toApi)
                .collect(toCollection(LinkedHashSet::new));
    }

    @Transactional
    public void delete(Long id) throws NotFoundException {
        var entity = repository.findByUserAndId(userService.getCurrentUser(), id)
                .orElseThrow(() -> new NotFoundException("Registro não encontrado"));

        entity.getTags().clear();
        repository.delete(entity);
    }
}
