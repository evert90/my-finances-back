package br.com.erp.converter.user;

import br.com.erp.api.user.User;
import br.com.erp.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static org.apache.tomcat.util.security.MD5Encoder.encode;

@Service
public class UserToUserEntity implements Function<User, UserEntity> {
    @Override
    public UserEntity apply(User user) {
        return new UserEntity(
                user.name(),
                user.email(),
                user.password()
        );
    }
}
