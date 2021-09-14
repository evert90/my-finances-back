package br.com.erp.service;

import br.com.erp.api.user.AuthenticatedUser;
import br.com.erp.api.user.User;
import br.com.erp.api.user.UserReadOnly;
import br.com.erp.converter.user.UserEntityToUserReadOnly;
import br.com.erp.converter.user.UserReadOnlyToAuthenticatedUser;
import br.com.erp.converter.user.UserToUserEntity;
import br.com.erp.entity.UserEntity;
import br.com.erp.repository.UserRepository;
import br.com.erp.service.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final UserEntityToUserReadOnly userEntityToUserReadOnly;

    private final UserToUserEntity userToUserEntity;

    private final UserReadOnlyToAuthenticatedUser userReadOnlyToAuthenticatedUser;

    public AuthenticatedUser authenticate(User user) {
        return ofNullable(get(user))
                .map(userReadOnlyToAuthenticatedUser)
                .orElseThrow(() -> new RuntimeException("Erro ao autenticar o usuário"));
    }

    public AuthenticatedUser saveAndAuthenticate(User user) {
        return userReadOnlyToAuthenticatedUser.apply(save(user));
    }

    public UserEntity getCurrentUser() {
        var userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return repository
                .findById(userDetails.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inválido ou não encontrado"));
    }

    private UserReadOnly get(User user) {
        return ofNullable(repository.findByEmailAndPassword(user.email(), user.password()))
                .map(userEntityToUserReadOnly)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }

    private UserReadOnly save(User user) {
        var entity = userToUserEntity.apply(user);
        entity = repository.save(entity);

        return userEntityToUserReadOnly.apply(entity);
    }


}
