package br.com.erp.repository;

import br.com.erp.entity.FinancialRecordRecurrenceEntity;
import br.com.erp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FinancialRecordRecurrenceRepository extends JpaRepository<FinancialRecordRecurrenceEntity, Long> {
    @Query("SELECT DISTINCT frr from FinancialRecordRecurrenceEntity frr" +
            " LEFT JOIN FETCH frr.tags" +
            " WHERE frr.user = :user" +
            " ORDER BY frr.name")
    List<FinancialRecordRecurrenceEntity> findByUserOrderByName(UserEntity user);

    Optional<FinancialRecordRecurrenceEntity> findByUserAndId(UserEntity user, Long id);
}
