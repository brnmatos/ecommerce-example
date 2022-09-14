package br.com.dpaulla.model.examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.User;

public class UsersExample {

	public User userAdmin() {
		User userAdmin = new User();
		userAdmin.setBairro("Itaim Bibi");
		userAdmin.setCelular("11999999999");
		userAdmin.setCep("04538133");
		userAdmin.setCidade("São Paulo");
		userAdmin.setComplemento("18º Andar");
		userAdmin.setCpf("44444444444");
		userAdmin.setDatanascimento("01/01/2001");
		userAdmin.setEstado("SP");
		userAdmin.setLogradouro("Av. Brigadeiro Faria Lima");
		userAdmin.setLogradouronumero("3477");
		userAdmin.setNome("Admin");
		userAdmin.setPassword("admin12345");
		userAdmin.setPasswordConfirm("admin12345");
		userAdmin.setSobrenome("Perfil");
		userAdmin.setTelefone("1188888888");
		userAdmin.setUsername("admin@example.com");
		userAdmin.setUsernameConfirm("admin@example.com");
		
		List<Role> permissionAdminDefault = new ArrayList<>();
    	Role role = new Role();
    	role.setName("administrador");
    	role.setRoleId(Long.valueOf(String.valueOf(1)));
    	permissionAdminDefault.add(role);

    	userAdmin.setRoles(new HashSet<>(permissionAdminDefault));
    	
		return userAdmin;
	}

	public User userVendor() {
		User userVendor = new User();
		userVendor.setBairro("Itaim Bibi");
		userVendor.setCelular("11999999999");
		userVendor.setCep("04538133");
		userVendor.setCidade("São Paulo");
		userVendor.setComplemento("18º Andar");
		userVendor.setCpf("44444444444");
		userVendor.setDatanascimento("01/01/2001");
		userVendor.setEstado("SP");
		userVendor.setLogradouro("Av. Brigadeiro Faria Lima");
		userVendor.setLogradouronumero("3477");
		userVendor.setNome("Vendor");
		userVendor.setPassword("vendor12345");
		userVendor.setPasswordConfirm("vendor12345");
		userVendor.setSobrenome("Perfil");
		userVendor.setTelefone("1188888888");
		userVendor.setUsername("vendor@example.com");
		userVendor.setUsernameConfirm("vendor@example.com");
		
		List<Role> permissionAdminDefault = new ArrayList<>();
    	Role role = new Role();
    	role.setName("vendedor");
    	role.setRoleId(Long.valueOf(String.valueOf(2)));
    	permissionAdminDefault.add(role);

    	userVendor.setRoles(new HashSet<>(permissionAdminDefault));
    	
		return userVendor;
	}

	public User userDefault() {
		User userDefault = new User();
		userDefault.setBairro("Itaim Bibi");
		userDefault.setCelular("11999999999");
		userDefault.setCep("04538133");
		userDefault.setCidade("São Paulo");
		userDefault.setComplemento("18º Andar");
		userDefault.setCpf("44444444444");
		userDefault.setDatanascimento("01/01/2001");
		userDefault.setEstado("SP");
		userDefault.setLogradouro("Av. Brigadeiro Faria Lima");
		userDefault.setLogradouronumero("3477");
		userDefault.setNome("Default");
		userDefault.setPassword("vendor12345");
		userDefault.setPasswordConfirm("vendor12345");
		userDefault.setSobrenome("Perfil");
		userDefault.setTelefone("1188888888");
		userDefault.setUsername("default@example.com");
		userDefault.setUsernameConfirm("default@example.com");
		
		List<Role> permissionAdminDefault = new ArrayList<>();
    	Role role = new Role();
    	role.setName("usuario");
    	role.setRoleId(Long.valueOf(String.valueOf(3)));
    	permissionAdminDefault.add(role);

    	userDefault.setRoles(new HashSet<>(permissionAdminDefault));
    	
		return userDefault;
	}
	
	
}
