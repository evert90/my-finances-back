package br.com.erp.service;

import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.User;
import br.com.erp.bean.user.UserReadonly;
import br.com.erp.converter.user.UserEntityToUserReadOnly;
import br.com.erp.converter.user.UserReadOnlyToAuthenticatedUser;
import br.com.erp.converter.user.UserToUserEntity;
import br.com.erp.entity.UserEntity;
import br.com.erp.repository.UserRepository;
import br.com.erp.service.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final UserEntityToUserReadOnly userEntityToUserReadOnly;

    private final UserToUserEntity userToUserEntity;

    private final UserReadOnlyToAuthenticatedUser userReadOnlyToAuthenticatedUser;

    private final PasswordEncoder passwordEncoder;

    public AuthenticatedUser authenticate(User user) {
        return ofNullable(user)
                .map(User::email)
                .map(repository::findByEmail)
                .filter(entity -> passwordEncoder.matches(user.password(), entity.getPassword()))
                .map(userEntityToUserReadOnly)
                .map(userReadOnlyToAuthenticatedUser)
                .orElseThrow(() -> new RuntimeException("Usuário e/ou senha inválidos"));
    }

    public AuthenticatedUser saveAndAuthenticate(User user) {
        return userReadOnlyToAuthenticatedUser.apply(save(user));
    }

    public AuthenticatedUser socialLoginAuthenticate(User user) {
        return ofNullable(user)
                .map(User::email)
                .map(repository::findByEmail)
                .map(userEntityToUserReadOnly)
                .map(userReadOnlyToAuthenticatedUser)
                .orElseGet(() -> userReadOnlyToAuthenticatedUser.apply(save(user)));
    }

    public UserEntity getCurrentUser() {
        var userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository
                .findById(userDetails.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inválido ou não encontrado"));
    }

    private UserReadonly save(User user) {
        return ofNullable(user)
                .map(userToUserEntity)
                .map(repository::save)
                .map(userEntityToUserReadOnly)
                .orElseThrow(() -> new RuntimeException("Erro ao salvar/retornar o usuário"));
    }

}
