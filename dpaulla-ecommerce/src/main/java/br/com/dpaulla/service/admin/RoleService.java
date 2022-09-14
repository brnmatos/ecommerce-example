package br.com.dpaulla.service.admin;

import br.com.dpaulla.model.Role;

public interface RoleService {
	
	public void save(Role role);
	public boolean checkEmpty();

}
