package br.com.dpaulla.service;

import br.com.dpaulla.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
	public boolean checkEmpty();
    void saveWithRoles(User user);
	
}