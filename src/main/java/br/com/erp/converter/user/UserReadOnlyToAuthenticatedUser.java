package br.com.erp.converter.user;

import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.UserReadonly;
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
        return AuthenticatedUser
                .builder()
                .user(user)
                .token(service.generateFromUser(user))
                .build();
    }
}
