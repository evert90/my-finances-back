package br.com.erp.repository;

import br.com.erp.api.financialrecord.FinancialRecordTotal;
import br.com.erp.api.financialrecord.FinancialRecordType;
import br.com.erp.entity.FinancialRecordEntity;
import br.com.erp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecordEntity, Long> {
    Set<FinancialRecordEntity> findByType(FinancialRecordType type);

    List<FinancialRecordEntity> findByUserOrderByDateDesc(UserEntity user);

    Set<FinancialRecordEntity> findByUserAndDateBetweenOrderByDateDesc(UserEntity user, LocalDate start, LocalDate end);

    @Query("SELECT new br.com.erp.api.financialrecord.FinancialRecordTotal(f.type, sum(f.value)) " +
            "FROM FinancialRecordEntity f " +
            "WHERE f.user = :user " +
            "GROUP BY f.type")
    Set<FinancialRecordTotal> getTotalReport(UserEntity user);

    @Query("SELECT new br.com.erp.api.financialrecord.FinancialRecordTotal(f.type, sum(f.value)) " +
            "FROM FinancialRecordEntity f " +
            "WHERE f.user = :user " +
            "AND f.date BETWEEN :start AND :end " +
            "GROUP BY f.type")
    Set<FinancialRecordTotal> getTotalReportByPeriod(LocalDate start, LocalDate end, UserEntity user);

    Optional<FinancialRecordEntity> findByUserAndId(UserEntity user, Long id);
}
