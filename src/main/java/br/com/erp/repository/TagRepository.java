package br.com.erp.repository;

import br.com.erp.entity.TagEntity;
import br.com.erp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findByUserOrderByNameAsc(UserEntity entity);
    Optional<TagEntity> findByUserAndName(UserEntity entity, String name);
}
