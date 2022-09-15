package br.com.dpaulla;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.User;
import br.com.dpaulla.model.examples.BrandsExample;
import br.com.dpaulla.model.examples.ImagesExample;
import br.com.dpaulla.model.examples.ProductsExample;
import br.com.dpaulla.model.examples.UsersExample;
import br.com.dpaulla.service.UserService;
import br.com.dpaulla.service.admin.ImageService;
import br.com.dpaulla.service.admin.MarcaService;
import br.com.dpaulla.service.admin.ProdutoService;
import br.com.dpaulla.service.admin.RoleService;

@Configuration
public class ApplicationInitializer implements ServletContextInitializer{
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	@Autowired
	MarcaService marcaService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ImageService imageService;
	
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		if (roleService.checkEmpty()) {
			roleStart(roleService);
		}
		
		if (userService.checkEmpty()) {
			userStart(userService);
		}
		
		if (marcaService.checkEmpty()) {
			brandStart(marcaService);
		}

		if (produtoService.checkEmpty()) {
			productStart(produtoService);
		}
		
		if (imageService.checkEmpty()) {
			imageStart(imageService);
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
	
	private void brandStart(MarcaService marcaService) {
		BrandsExample brandExample = new BrandsExample();
		marcaService.save(brandExample.firstBrand());
		marcaService.save(brandExample.secondBrand());
		marcaService.save(brandExample.thirdBrand());
		marcaService.save(brandExample.fourthBrand());
	}
	
	private void productStart(ProdutoService produtoService) {
		ProductsExample productExample = new ProductsExample();
		produtoService.save(productExample.firstProduct());
		produtoService.save(productExample.secondProduct());
		produtoService.save(productExample.thirdProduct());
		produtoService.save(productExample.fourthProduct());
		produtoService.save(productExample.fifthProduct());
		produtoService.save(productExample.sixthProduct());
	}
	
	private void imageStart(ImageService imageService) {
		ImagesExample imageExample = new ImagesExample();
		imageService.save(imageExample.firstImage());
		imageService.save(imageExample.secondImage());
		imageService.save(imageExample.thirdImage());
		imageService.save(imageExample.fourthImage());
		imageService.save(imageExample.fifthImage());
		imageService.save(imageExample.sixthImage());
		imageService.save(imageExample.seventhImage());
		imageService.save(imageExample.eighthImage());
	}
	

}
