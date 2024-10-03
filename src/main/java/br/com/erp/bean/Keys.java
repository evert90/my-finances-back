package br.com.erp.bean;

import lombok.Builder;

@Builder(toBuilder = true)
public record Keys(String p256dh, String auth) {
}
