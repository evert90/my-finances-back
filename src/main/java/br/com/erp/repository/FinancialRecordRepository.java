package br.com.erp.repository;

import br.com.erp.bean.tag.TagTotalDTO;
import br.com.erp.bean.financialrecord.FinancialRecordTotal;
import br.com.erp.bean.financialrecord.FinancialRecordType;
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

    List<FinancialRecordEntity> findFirst150ByUserOrderByDateDesc(UserEntity user);

    Set<FinancialRecordEntity> findByUserAndDateBetweenOrderByDateDesc(UserEntity user, LocalDate start, LocalDate end);

    List<FinancialRecordEntity> findByUserAndNotificationAndPaidAndTypeAndDateBetweenOrderByDateDesc(
            UserEntity user,
            Boolean notification,
            Boolean paid,
            FinancialRecordType type,
            LocalDate start,
            LocalDate end);

    Long countByUserAndDateAndName(UserEntity user, LocalDate date, String name);

    @Query("SELECT new br.com.erp.bean.financialrecord.FinancialRecordTotal(f.type, sum(f.value)) " +
            "FROM FinancialRecordEntity f " +
            "WHERE f.user = :user " +
            "GROUP BY f.type")
    Set<FinancialRecordTotal> getTotalReport(UserEntity user);

    @Query("SELECT new br.com.erp.bean.financialrecord.FinancialRecordTotal(f.type, sum(f.value)) " +
            "FROM FinancialRecordEntity f " +
            "WHERE f.user = :user " +
            "AND f.date BETWEEN :start AND :end " +
            "GROUP BY f.type")
    Set<FinancialRecordTotal> getTotalReportByPeriod(LocalDate start, LocalDate end, UserEntity user);

    @Query(nativeQuery = true)
    Set<TagTotalDTO> getTotalReportByPeriodAndTagIds(LocalDate start, LocalDate end, Long userId, Set<Long> tagIds);

    Optional<FinancialRecordEntity> findByUserAndId(UserEntity user, Long id);
}
