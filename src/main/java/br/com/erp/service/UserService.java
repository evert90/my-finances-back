package br.com.erp.service;

import br.com.erp.api.user.User;
import br.com.erp.api.user.UserReadOnly;
import br.com.erp.converter.user.UserEntityToUserReadOnly;
import br.com.erp.converter.user.UserToUserEntity;
import br.com.erp.entity.UserEntity;
import br.com.erp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final UserEntityToUserReadOnly userEntityToUserReadOnly;

    private final UserToUserEntity userToUserEntity;

    public UserReadOnly save(User user) {
        UserEntity entity = userToUserEntity.apply(user);
        entity = repository.save(entity);
        return userEntityToUserReadOnly.apply(entity);
    }

}
