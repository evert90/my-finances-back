package br.com.erp.controller;

import br.com.erp.bean.notification.PushSubscription;
import br.com.erp.service.notification.PushSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/push-subscription")
@RequiredArgsConstructor
public class PushSubscriptionController {

    private final PushSubscriptionService service;

    @PostMapping
    public void subscribe(@RequestBody PushSubscription subscription) {
        service.save(subscription);
    }
}
