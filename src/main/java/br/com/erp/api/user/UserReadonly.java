package br.com.erp.api.user;

import br.com.erp.api.Role;

import java.util.Set;

public record UserReadonly(Long id, String name, String email, Set<Role> roles) { }