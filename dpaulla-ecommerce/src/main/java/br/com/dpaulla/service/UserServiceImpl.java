package br.com.dpaulla.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.User;
import br.com.dpaulla.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
    	List<Role> permissionUserDefault = new ArrayList<>();
    	Role roleTemp = new Role();
    	roleTemp.setName("usuario");
    	roleTemp.setRoleId(Long.valueOf(String.valueOf(3)));
    	permissionUserDefault.add(roleTemp);
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setRoles(new HashSet<>(permissionUserDefault));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}