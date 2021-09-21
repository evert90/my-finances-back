package br.com.erp.service;

import br.com.erp.api.FinancialRecord;
import br.com.erp.api.FinancialRecordType;
import br.com.erp.converter.financialrecord.FinancialRecordEntityToFinanacialRecord;
import br.com.erp.converter.financialrecord.FinancialRecordToFinancialRecordEntity;
import br.com.erp.repository.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Service
public class FinancialRecordService {
    private final FinancialRecordRepository repository;

    private final FinancialRecordToFinancialRecordEntity toEntity;

    private final FinancialRecordEntityToFinanacialRecord toApi;

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

    public Set<FinancialRecord> getAll() {
        return repository.findByUserOrderByDateDesc(userService.getCurrentUser())
                .stream()
                .map(toApi)
                .collect(toCollection(LinkedHashSet::new));
    }
}
