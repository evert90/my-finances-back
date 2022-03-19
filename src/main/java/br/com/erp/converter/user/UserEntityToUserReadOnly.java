package br.com.erp.converter.user;

import br.com.erp.bean.user.UserReadonly;
import br.com.erp.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.Collections.singleton;

@Service
public class UserEntityToUserReadOnly implements Function<UserEntity, UserReadonly> {
    @Override
    public UserReadonly apply(UserEntity userEntity) {
        return UserReadonly
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .roles(singleton(userEntity.getRole()))
                .build();
    }
}
