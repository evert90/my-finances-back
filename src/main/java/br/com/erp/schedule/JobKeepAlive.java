package br.com.erp.schedule;

import br.com.erp.client.KeepAliveClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobKeepAlive {

    private final KeepAliveClient client;

    @Value("${app.keepalive:false}")
    private boolean keepAlive;

    @Scheduled(fixedDelay = 60000 * 20) //20 MINUTES
    public void run() {
        if(keepAlive) {
            log.info("Ping");
            client.ping();
        }
    }

}
