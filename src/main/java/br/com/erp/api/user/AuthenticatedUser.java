package br.com.erp.api.user;

public record AuthenticatedUser (UserReadonly user, String token) { }
