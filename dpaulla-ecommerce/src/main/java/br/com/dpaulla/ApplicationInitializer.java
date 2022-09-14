package br.com.dpaulla;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.User;
import br.com.dpaulla.model.examples.UsersExample;
import br.com.dpaulla.service.UserService;
import br.com.dpaulla.service.admin.ProdutoService;
import br.com.dpaulla.service.admin.RoleService;

@Configuration
public class ApplicationInitializer implements ServletContextInitializer{
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		if (roleService.checkEmpty()) {
			roleStart(roleService);
		}
		
		if (userService.checkEmpty()) {
			userStart(userService);
		}

		if (produtoService.checkEmpty()) {
			produtoStart(produtoService);
		}
		
		
	}
	
	private void roleStart(RoleService roleService) {
		Role roleAdmin = new Role(); roleAdmin.setRoleId(Long.valueOf(1)); roleAdmin.setName("administrador");
		roleService.save(roleAdmin);
		Role roleVendor = new Role(); roleVendor.setRoleId(Long.valueOf(2)); roleVendor.setName("vendedor");
		roleService.save(roleVendor);
		Role roleUser = new Role(); roleUser.setRoleId(Long.valueOf(3)); roleUser.setName("usuario");
		roleService.save(roleUser);
	}
	
	private void userStart(UserService userService) {
		UsersExample userExample = new UsersExample();
		User userAdmin = userExample.userAdmin();
		userService.saveWithRoles(userAdmin);

		User userVendor = userExample.userVendor();
		userService.saveWithRoles(userVendor);

		User userDefault = userExample.userDefault();
		userService.saveWithRoles(userDefault);
		
	}
	
	private void produtoStart(ProdutoService produtoService) {
		
	}
	

}
