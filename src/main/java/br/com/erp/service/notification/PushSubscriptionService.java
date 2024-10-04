package br.com.erp.service.notification;

import br.com.erp.bean.notification.Keys;
import br.com.erp.bean.notification.PushSubscription;
import br.com.erp.entity.PushSubscriptionEntity;
import br.com.erp.repository.PushSubscriptionRepository;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushSubscriptionService {

    private final PushSubscriptionRepository repository;

    private final UserService userService;

    public void save(PushSubscription pushSubscription) {
        var subscription = repository.findByEndpointAndAuthKeyAndP256dhKey(
                pushSubscription.endpoint(),
                pushSubscription.keys().auth(),
                pushSubscription.keys().p256dh()
        );

        var user = userService.getCurrentUser();

        if(subscription == null) {
            subscription = PushSubscriptionEntity
                    .builder()
                    .endpoint(pushSubscription.endpoint())
                    .authKey(pushSubscription.keys().auth())
                    .p256dhKey(pushSubscription.keys().p256dh())
                    .user(user)
                    .build();
            repository.save(subscription);
        } else {
            if(!subscription.getUser().equals(user)) {
                subscription.setUser(user);
                repository.save(subscription);
            }
        }
    }

    public List<PushSubscriptionEntity> getAll() {
        return repository.findAll();
    }

    public PushSubscription getPushSubscription(PushSubscriptionEntity entity) {
        return PushSubscription
                .builder()
                .endpoint(entity.getEndpoint())
                .keys(Keys
                        .builder()
                        .auth(entity.getAuthKey())
                        .p256dh(entity.getP256dhKey())
                        .build())
                .build();
    }
}
