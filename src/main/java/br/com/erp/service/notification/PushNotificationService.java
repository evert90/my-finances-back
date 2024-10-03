package br.com.erp.service.notification;

import br.com.erp.bean.PushNotificationMessage;
import br.com.erp.bean.PushSubscription;
import br.com.erp.entity.AssetEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.format.DateTimeFormatter;

@Service
public class PushNotificationService {

    @Value("${vapid.public-key}")
    private String publicKey;

    @Value("${vapid.private-key}")
    private String privateKey;

    @Value("${vapid.subject}")
    private String subject;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PushNotificationService() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public void sendPushNotification(PushSubscription subscription, PushNotificationMessage message) throws Exception {

        Notification notification = new Notification(
                subscription.endpoint(),
                subscription.keys().p256dh(),
                subscription.keys().auth(),
                objectMapper.writeValueAsString(message)
        );

        PushService pushService = new PushService()
                .setPublicKey(Utils.loadPublicKey(publicKey))
                .setPrivateKey(Utils.loadPrivateKey(privateKey))
                .setSubject(subject);

        pushService.send(notification);
    }

    public PushNotificationMessage getMessage(AssetEntity asset) {
        return PushNotificationMessage
                .builder()
                .title("Aviso de vencimento")
                .message("Seu investimento do banco %s irá vencer em %s"
                        .formatted(asset.getBank(), asset.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .build();
    }
}
