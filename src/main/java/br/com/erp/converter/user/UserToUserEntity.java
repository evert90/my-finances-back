package br.com.erp.converter.user;

import br.com.erp.api.user.User;
import br.com.erp.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class UserToUserEntity implements Function<User, UserEntity> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity apply(User user) {
        return new UserEntity(
                user.name(),
                user.email(),
                passwordEncoder.encode(user.password())
        );
    }
}
