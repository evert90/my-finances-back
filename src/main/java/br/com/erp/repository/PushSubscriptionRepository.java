package br.com.erp.repository;

import br.com.erp.entity.PushSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushSubscriptionRepository extends JpaRepository<PushSubscriptionEntity, Long> {
    PushSubscriptionEntity findByEndpointAndAuthKeyAndP256dhKey(String endpoint, String authKey, String p256dhKey);
}
