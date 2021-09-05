package br.com.erp.service.security;

import br.com.erp.converter.user.UserEntityToUserReadOnly;
import br.com.erp.entity.UserEntity;
import br.com.erp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	private final UserEntityToUserReadOnly userEntityToUserReadOnly;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = ofNullable(userRepository.findByEmail(email))
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

		return UserDetailsImpl.build(userEntityToUserReadOnly.apply(user));
	}

}
