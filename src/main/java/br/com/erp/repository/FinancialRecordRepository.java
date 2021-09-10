package br.com.erp.repository;

import br.com.erp.api.FinancialRecordType;
import br.com.erp.entity.FinancialRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecordEntity, Long> {
    Set<FinancialRecordEntity> findByType(FinancialRecordType type);
}
