package br.com.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keepalive")
@Slf4j
public class KeepAliveController {

    @GetMapping
    void keepalive() {
        log.info("Keepalive");
    }
}