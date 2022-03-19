package br.com.erp.bean.user;

import lombok.Builder;

public record AuthenticatedUser (UserReadonly user, String token) {
    @Builder
    public AuthenticatedUser {}
}
