package br.com.erp.converter.user;

import br.com.erp.api.user.UserReadonly;
import br.com.erp.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.Collections.singleton;

@Service
public class UserEntityToUserReadOnly implements Function<UserEntity, UserReadonly> {
    @Override
    public UserReadonly apply(UserEntity userEntity) {
        return new UserReadonly(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                singleton(userEntity.getRole())
        );
    }
}
