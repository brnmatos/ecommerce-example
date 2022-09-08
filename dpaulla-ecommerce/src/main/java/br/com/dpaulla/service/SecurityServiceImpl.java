package br.com.dpaulla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import br.com.dpaulla.model.User;
import br.com.dpaulla.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
		    UserDetails autDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    log.info("AUTH DETAILS: {}", autDetails);
			return ((UserDetails) autDetails).getUsername();

		} else {
			return "anonymousUser";
		}
	}

	@Override
	public void autoLogin(String username, String password) {
		log.info("AUTO LOGIN: {} PASSWD: {}", username , password);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken); 

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.info(String.format("Auto login %s successfully!", username));
			log.info("Security Service: autoLogin {}", username);
		}
	}
	
	@Override
	public User findUserLogged() {
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
		    UserDetails autDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String username = autDetails.getUsername();
		    User user = userRepository.findByUsername(username);
		    
		    return user;
		} else {
			User user = new User();
			user.setUsername("anonymousUser");
			user.setNome("anonymousUser");
			return user;
		}
	}

}