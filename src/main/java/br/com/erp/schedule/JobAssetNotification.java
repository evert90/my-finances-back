package br.com.erp.schedule;

import br.com.erp.entity.AssetEntity;
import br.com.erp.entity.UserEntity;
import br.com.erp.repository.AssetRepository;
import br.com.erp.service.notification.PushNotificationService;
import br.com.erp.service.notification.PushSubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static br.com.erp.bean.asset.AssetType.RENDA_FIXA;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobAssetNotification {

    private final AssetRepository assetRepository;

    private final PushNotificationService pushNotificationService;

    private final PushSubscriptionService pushSubscriptionService;

    @Scheduled(cron = "0 0 11 * * ?") // 11h
    public void run() {
        log.info("Starting asset notification job");

        pushSubscriptionService
                .getAll()
                .forEach(entity -> {
                    getAssets(entity.getUser()).forEach(asset -> {
                        try {
                            log.info("Sending push notification to {}", entity.getUser().getEmail());
                            pushNotificationService.sendPushNotification(
                                    pushSubscriptionService.getPushSubscription(entity),
                                    pushNotificationService.getMessage(asset)
                            );
                        } catch (Exception e) {
                            throw new RuntimeException("Error sending push notification to " + entity.getUser().getEmail(), e);
                        }
                    });
                });

        log.info("Finished asset notification job");
    }

    private List<AssetEntity> getAssets(UserEntity user) {
        return assetRepository.findByUserAndTypeAndEndDateBetween(user, RENDA_FIXA, LocalDate.now(), LocalDate.now().plusDays(1));
    }
}
