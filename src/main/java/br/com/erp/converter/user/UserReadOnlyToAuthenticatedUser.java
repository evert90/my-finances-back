package br.com.erp.converter.user;

import br.com.erp.api.user.AuthenticatedUser;
import br.com.erp.api.user.UserReadonly;
import br.com.erp.service.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class UserReadOnlyToAuthenticatedUser implements Function<UserReadonly, AuthenticatedUser> {

    private final JwtService service;

    @Override
    public AuthenticatedUser apply(UserReadonly user) {
        return new AuthenticatedUser(user, service.generateFromUser(user));
    }
}
