package br.com.dpaulla.mail.server.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import br.com.dpaulla.mail.client.impl.MailClientImpl;
import br.com.dpaulla.mail.server.utils.MailWrapper;
import br.com.dpaulla.model.wrapper.CadastroWrapper;

@RestController
@RequestMapping(value = "/mail")
public class MainController {

	private final RestTemplate client = new RestTemplate();
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);

	@Value("${app.mailgun.key}")
	private String password;

	@Value("${app.mailgun.url}")
	private String url;

	@Value("${app.mail.platform.contact.from}")
	private String emailFromMailgun;

	@Value("${app.mail.cadastro.subject}")
	private String cadastroMailSubject;

	@Value("${app.mail.cadastro.template}")
	private String cadastroTemplate;

	@RequestMapping(value = {
			"/cadastro" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cadastroEmail(@RequestBody CadastroWrapper cadastroWrapper) throws Exception {
		log.info("Enter on: /Cadastro");

		HttpHeaders httpHeaders = createHeaders(password);
		httpHeaders.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MailWrapper mailWrapper = buildCadastroMessage(cadastroWrapper);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		map.add("from", mailWrapper.getFrom());
		map.add("to", mailWrapper.getTo());
		map.add("subject", mailWrapper.getSubject());
		map.add("text", mailWrapper.getMail());

		log.info("valores: {}, {}, {}, {}, {}, {}", mailWrapper.getFrom(), mailWrapper.getTo(), mailWrapper.getSubject(), mailWrapper.getBcc(), mailWrapper.getMail());
		
		ResponseEntity<String> response = (ResponseEntity<String>) client.exchange(url, HttpMethod.POST,
				new HttpEntity<>(map, httpHeaders), String.class);

		if (response.getStatusCodeValue() > 0) {
			log.info("return: {} e body: {}", response.getStatusCode(), response.getBody());
		}

		log.info("Email OK! {}", response.getBody());

		return response;
	}

	private HttpHeaders createHeaders(String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = "api" + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	public MailWrapper buildCadastroMessage(CadastroWrapper cadastroWrapper) {
		MailWrapper mailWrapper = new MailWrapper();
		Map<String, Object> aditional = new HashMap<>();
		// aditional.put("VARIAVEL NO TEMPLATE", "VALOR QUE DEVE ASSUMIR");
		aditional.put("nome", cadastroWrapper.getUserCadastrado().getNome());
		mailWrapper.setSubject(cadastroMailSubject);
		mailWrapper.setTemplate(cadastroTemplate);
		mailWrapper.setAditional(aditional);
		mailWrapper.setFrom(emailFromMailgun);
		mailWrapper.setTo(cadastroWrapper.getUserCadastrado().getUsername());

		return mailWrapper;
	}

	public ResponseEntity<String> send(MailWrapper mailWrapper) throws Exception {
		log.info("ENVIA email '{}' to: {}", mailWrapper.getSubject(), mailWrapper.getTo());

		HttpHeaders httpHeaders = createHeaders(password);
		httpHeaders.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("from", mailWrapper.getFrom());
		map.add("to", mailWrapper.getTo());
		map.add("subject", mailWrapper.getSubject());
		map.add("bcc", mailWrapper.getBcc());
		map.add("text", mailWrapper.getMail());
		ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, new HttpEntity<>(map, httpHeaders),
				String.class);
		return response;
	}

}
