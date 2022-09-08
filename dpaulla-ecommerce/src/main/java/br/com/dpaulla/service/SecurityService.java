package br.com.dpaulla.service;

import br.com.dpaulla.model.User;

public interface SecurityService {
    public String findLoggedInUsername();

    public void autoLogin(String username, String password);
    
    public User findUserLogged();
    
}