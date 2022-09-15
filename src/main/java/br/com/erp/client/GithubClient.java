package br.com.erp.client;

import br.com.erp.bean.GithubEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GithubClient {
    @GetMapping(value = "/user/emails")
    List<GithubEmail> getEmails(@RequestHeader(value = "Authorization") String authorizationHeader);
}
