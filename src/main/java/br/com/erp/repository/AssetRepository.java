package br.com.erp.repository;

import br.com.erp.entity.AssetEntity;
import br.com.erp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
    Optional<AssetEntity> findByUserAndId(UserEntity user, Long id);

    List<AssetEntity> findByUserOrderByEndDateAsc(UserEntity user);
}
