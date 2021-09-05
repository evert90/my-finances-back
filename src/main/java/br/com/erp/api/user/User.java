package br.com.erp.api.user;

public record User (Long id, String name, String email, String password) { }