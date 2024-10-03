package br.com.erp.service.social;

import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.User;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoogleSocialLoginService implements SocialLoginService {

    private final UserService userService;

    @Override
    public AuthenticatedUser apply(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        var user = User
                .builder()
                .name(Objects.requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("name")).toString())
                .email(Objects.requireNonNull(oAuth2AuthenticationToken.getPrincipal().getAttribute("email")).toString())
                .password(UUID.randomUUID().toString())
                .build();

        return userService.socialLoginAuthenticate(user);
    }

    @Override
    public Boolean supports(String authorizedClientRegistrationId) {
        return authorizedClientRegistrationId.equals("google");
    }
}
