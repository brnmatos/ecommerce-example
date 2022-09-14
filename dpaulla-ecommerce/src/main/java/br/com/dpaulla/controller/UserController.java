package br.com.dpaulla.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.dpaulla.component.UserValidator;
import br.com.dpaulla.component.Util;
import br.com.dpaulla.mail.client.MailClient;
import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.User;
import br.com.dpaulla.model.response.MailResponse;
import br.com.dpaulla.model.wrapper.CadastroWrapper;
import br.com.dpaulla.service.SecurityService;
import br.com.dpaulla.service.UserService;

@Controller
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private MailClient mailClient;

	@GetMapping("/cadastro")
	public String cadastro(ModelMap model, HttpSession session) {
		log.info("enter on cadastro: {}");
		User user = securityService.findUserLogged();
		
		if (!user.getUsername().toString().equals("anonymousUser")) {
			model.addAttribute("userForm", user);
			model.put("user", user);
			return "perfil";
		}else {
			User userEnderecoSession = (User) session.getAttribute("SESSION_USER_ENDERECO");
			if (userEnderecoSession != null) {
				userEnderecoSession.setNome("anonimo");
			}
			model.addAttribute("userForm", new User());
			model.put("user", userEnderecoSession);
			return "cadastro";
		}
	}

	@PostMapping("/cadastro")
	public String cadastro(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws InterruptedException, ExecutionException, TimeoutException {
		userValidator.validate(userForm, bindingResult);

		Util util = new Util();
		if (bindingResult.hasErrors()) {
			return "cadastro";
		}

		log.info("no controller os valores é: {}", userForm);
		userForm.setCpf(util.tratarCPF(userForm.getCpf()));
		
		userService.save(userForm);
		
		Future<ResponseEntity<MailResponse>> response = mailClient.sendCadastroEmail(new CadastroWrapper(userForm));

		ResponseEntity<MailResponse> mailResponse = response.get(10, TimeUnit.SECONDS);
		if(mailResponse.getStatusCode().is2xxSuccessful()) {
			log.info("email enviado com sucesso!");
		}else {
			log.info("email falhou!");
		}
			

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout, HttpSession session) {
		if (error != null) {
			model.addAttribute("error", "Seu usuário ou senha está incorreto.");
		}
		if (logout != null)
			model.addAttribute("message", "Você entrou na plataforma.");

		return "login";
	}
	
	@GetMapping({ "/" })
	public String main(ModelMap model) {
		
		User user = securityService.findUserLogged();
		log.info("usuário logado: {}, e {}", user.getNome(), user.getUsername());
		model.put("user", user);
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		Role roleTemp = new Role();
		if (!user.getUsername().toString().equals("anonymousUser")) {
			roleTemp = user.getRoles().stream().findFirst().get();
			log.info("roleTemp: {}", roleTemp);
		
		} else {
			roleTemp.setRoleId(Long.valueOf(String.valueOf(3)));
			roleTemp.setName("user");
		}

		model.put("role", roleTemp);
 		 		
		return "dpaulla";
	}
	
	@PostMapping("/goLogin")
	public String usernameSession(@ModelAttribute("username") String msg, HttpServletRequest request) {
		log.info("AS INFORMAÇÕES NO MSG: {}", msg);

		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_USER");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_USER", messages);
		}
		messages.add(msg);
		request.getSession().setAttribute("MY_SESSION_USER", messages);
		log.info("AS INFORMAÇÕES NO HTTPREQUEST: {}", request.getSession().getAttribute("MY_SESSION_USER"));
		return "redirect:/session";
	}
	
	@GetMapping("/logout")
	public String destroySessionAndExit(HttpServletRequest request) {
		log.info("logouting...");
		request.getSession().invalidate();
		return "redirect:/login";
	}	
	
	
	/********************************************EXAMPLE SESSION *************************************************/
	
	@GetMapping("/session")
	public String process(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("sessionMessages", messages);

		return "index";
	}

	@PostMapping("/persistMessage")
	public String persistMessage(@ModelAttribute("msg") String msg, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		}
		messages.add(msg);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		
		log.info("AS INFORMAÇÕES NO MSG: {}", msg);
		log.info("AS INFORMAÇÕES NO HTTPREQUEST: {}", request.getSession().getAttribute("MY_SESSION_MESSAGES"));
		
		return "redirect:/session";
	}
	
}