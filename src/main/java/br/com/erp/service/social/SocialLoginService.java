package br.com.erp.service.social;

import br.com.erp.bean.user.AuthenticatedUser;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.function.Function;

public interface SocialLoginService extends Function<OAuth2AuthenticationToken, AuthenticatedUser> {
    Boolean supports(String authorizedClientRegistrationId);
}


