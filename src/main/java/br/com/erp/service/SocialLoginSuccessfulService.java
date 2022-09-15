package br.com.erp.service;

import br.com.erp.bean.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialLoginSuccessfulService {
    private final List<SocialLoginService> socialLoginServices;

    public AuthenticatedUser authenticate(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return socialLoginServices
                .stream()
                .filter(it -> it.supports(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId()))
                .findFirst()
                .map(socialLoginService -> socialLoginService.apply(oAuth2AuthenticationToken))
                .orElseThrow(() -> new RuntimeException("Provider de autenticação não encontrado"));
    }

    public String getTokenAsUrlParam(AuthenticatedUser authenticatedUser) {
        return "?token=".concat(UriUtils.encode(authenticatedUser.token(), "UTF-8"));
    }

}
