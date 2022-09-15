package br.com.erp.service;

import br.com.erp.bean.GithubEmail;
import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.User;
import br.com.erp.client.GithubClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class GithubSocialLoginService implements SocialLoginService {

    private final UserService userService;

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    private final GithubClient githubClient;

    @Override
    public AuthenticatedUser apply(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return Optional.of(User
                .builder()
                .name(getName(oAuth2AuthenticationToken))
                .email(getEmail(oAuth2AuthenticationToken))
                .password(UUID.randomUUID().toString())
                .build())
                .map(userService::socialLoginAuthenticate)
                .orElseThrow(() -> new RuntimeException("Não foi possível autenticar utilizando o Github"));
    }

    @Override
    public Boolean supports(String authorizedClientRegistrationId) {
        return authorizedClientRegistrationId.equals("github");
    }

    private String getName(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttribute("name") != null ?
                requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("name")).toString() :
                requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("login")).toString();
    }

    private String getEmail(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttribute("email") != null ?
                requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("email")).toString() :
                getEmailByToken(oAuth2AuthenticationToken);
    }

    private String getEmailByToken(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String tokenValue = oAuth2AuthorizedClientService.loadAuthorizedClient(
                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                oAuth2AuthenticationToken.getName())
                .getAccessToken()
                .getTokenValue();

        return githubClient
                .getEmails("token ".concat(tokenValue))
                .stream()
                .filter(GithubEmail::primary)
                .findFirst()
                .map(GithubEmail::email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }
}
