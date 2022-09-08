package br.com.dpaulla.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.User;
import br.com.dpaulla.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		log.info("INICIO DE TUDO USER DETAILS SERVICE IMPL: {}", username);
		User user = userRepository.findByUsername(username);
		log.info("antes do IF: {}", user);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		log.info("PERMISSÃO: {}", user.getRoles());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		for (Role role: user.getRoles()) {
			log.info("PERMISSÃO: {}", role.getName());
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		  		
		log.info("UserDetailsServiceImpl: User: '{}' Passwd: '{}', roles: '{}'", user.getNome(), user.getPassword(), grantedAuthorities);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
		
	}
}