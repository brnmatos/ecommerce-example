package br.com.dpaulla.controller.impl;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import br.com.dpaulla.controller.MainController;
import br.com.dpaulla.model.User;
import br.com.dpaulla.model.response.MailResponse;
import br.com.dpaulla.model.wrapper.CadastroWrapper;
import br.com.dpaulla.service.MailgunMailService;
import br.com.dpaulla.utils.CadastroMailMessageUtil;
import br.com.dpaulla.utils.MailWrapper;

@RestController
@RequestMapping(value = "/mail/cadastro")
public class CadastroEmailController extends MainController<CadastroWrapper>{
	
	private static Logger log = LoggerFactory.getLogger(CadastroEmailController.class);

	public CadastroEmailController(MailgunMailService mailgunMailService) {
		super(mailgunMailService);
	}
	@Autowired
	private ExecutorService executor;
	@Autowired
	private CadastroMailMessageUtil mailMessage;
	
	/*
	 * @Autowired public CadastroEmailHourController(ExecutorService executor,
	 * MailgunMailService mailgunMailService, CadastroMailMessageUtil mailMessage) {
	 * this.executor = executor; this.mailMessage = mailMessage; }
	 */
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<MailResponse>> alerts(@RequestBody CadastroWrapper cadastro){
		DeferredResult<ResponseEntity<MailResponse>> response = new DeferredResult<ResponseEntity<MailResponse>>();
		log.info("Received request. User -> {}", cadastro);
		executor.submit(sendMail(response, cadastro));
		return response;	
	}
	
	@Override
	protected String okMessage(CadastroWrapper cadastro) {
		return String.format("Email enviado com sucesso para o email: crbsistemas@rotadasbandeiras.com.br");
	}

	@Override
	protected String errorMessage(CadastroWrapper cadastro) {
		return String.format("Erro ao tentar enviar email para o email: crbsistemas@rotadasbandeiras.com.br ");
	}

	@Override
	protected MailWrapper buildMessage(CadastroWrapper cadastro) {
		User user = cadastro.getUserCadastrado();
		return mailMessage.buildCadastroEmailMessage(user);

	}
}
