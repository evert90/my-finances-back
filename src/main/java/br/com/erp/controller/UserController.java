package br.com.erp.controller;

import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.User;
import br.com.erp.service.SocialLoginSuccessfulService;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    private final SocialLoginSuccessfulService socialLoginSuccessfulService;

    @Value("${app.auth.redirectUrl:}")
    private String redirectUrl;

    @PostMapping("/")
    AuthenticatedUser save(@RequestBody User user) {
        return userService.saveAndAuthenticate(user);
    }

    @PostMapping("/auth")
    AuthenticatedUser authenticate(@RequestBody User user) {
        return userService.authenticate(user);
    }

    @GetMapping("/auth/social/success")
    RedirectView socialLoginSuccess(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        if(oAuth2AuthenticationToken == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/api/users/auth/logout");
            return redirectView;
        }
        var user = socialLoginSuccessfulService.authenticate(oAuth2AuthenticationToken);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl.concat(socialLoginSuccessfulService.getTokenAsUrlParam(user)));
        return redirectView;
    }

    @GetMapping("/auth/social/failure")
    RedirectView socialLoginFailure() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl.concat("?socialLoginFailure=true"));
        return redirectView;
    }

    @GetMapping("/auth/logout/success")
    RedirectView logoutSuccess() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl.concat("?logoutSuccess=true"));
        return redirectView;
    }
}
