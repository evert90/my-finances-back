package br.com.erp.bean.user;

import lombok.Builder;

import java.util.Set;

public record User (Long id, String name, String email, String password, Set<Role> roles) {
    @Builder
    public User {}
}