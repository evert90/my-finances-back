package br.com.erp.api.user;

public record AuthenticatedUser (UserReadOnly user, String token) { }
