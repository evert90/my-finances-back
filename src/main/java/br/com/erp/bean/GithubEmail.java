package br.com.erp.bean;

public record GithubEmail(String email, Boolean primary, Boolean verified, String visibility) {
}
