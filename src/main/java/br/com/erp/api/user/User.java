package br.com.erp.api.user;

import br.com.erp.api.Role;

import java.util.Set;

public record User (Long id, String name, String email, String password, Set<Role> roles) { }