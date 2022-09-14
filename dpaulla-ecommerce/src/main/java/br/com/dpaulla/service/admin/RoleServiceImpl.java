package br.com.dpaulla.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dpaulla.model.Role;
import br.com.dpaulla.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public boolean checkEmpty() {
		if(roleRepository.count() < 1) {
			return true;
		}else {return false;}
	}

}
