package br.com.erp.service;

import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class GithubSocialLoginService implements SocialLoginService {

    private final UserService userService;

    @Override
    public AuthenticatedUser apply(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        var login = requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("login")).toString();
        var email = oAuth2AuthenticationToken.getPrincipal().getAttribute("email") != null ?
                requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("email")).toString() :
                login.concat("@github.com");

        var user = User
                .builder()
                .name(login)
                .email(email)
                .password(UUID.randomUUID().toString())
                .build();

        return userService.socialLoginAuthenticate(user);
    }

    @Override
    public Boolean supports(String authorizedClientRegistrationId) {
        return authorizedClientRegistrationId.equals("github");
    }
}
