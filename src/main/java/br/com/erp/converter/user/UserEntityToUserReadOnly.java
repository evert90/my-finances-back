package br.com.erp.converter.user;

import br.com.erp.api.user.UserReadOnly;
import br.com.erp.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.Collections.singleton;

@Service
public class UserEntityToUserReadOnly implements Function<UserEntity, UserReadOnly> {
    @Override
    public UserReadOnly apply(UserEntity userEntity) {
        return new UserReadOnly(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                singleton(userEntity.getRole())
        );
    }
}
