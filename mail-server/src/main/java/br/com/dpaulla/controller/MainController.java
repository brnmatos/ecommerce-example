package br.com.dpaulla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import br.com.dpaulla.model.response.MailResponse;
import br.com.dpaulla.service.MailgunMailService;
import br.com.dpaulla.utils.MailWrapper;

public abstract class MainController<T> {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);
	
	private MailgunMailService mailgunMailService;
	
	public MainController( MailgunMailService mailgunMailService) {
		this.mailgunMailService = mailgunMailService;
	}
	
	protected abstract String okMessage(T request);
	protected abstract String errorMessage(T request);
	protected abstract MailWrapper buildMessage(T object);
	
	protected Runnable sendMail( DeferredResult<ResponseEntity<MailResponse>> response, T request ) {
		return () -> {
			
			MailResponse resp = null;
			ResponseEntity<MailResponse> processResult = null;
			try {
				MailWrapper mail = buildMessage(request);
				log.info("Message wrapper -> {}", mail);
				
				ResponseEntity<String> mailgunResponse = mailgunMailService.send(mail, null);
				log.info("Mail Response -> {}", mailgunResponse);
				mailgunResponse.wait(100);
				if(!mailgunResponse.getStatusCode().is2xxSuccessful()) {
					log.info("Email not send!!!");
					if(mailgunResponse.hasBody() && log.isDebugEnabled()) {
						log.info("Error details -> {}", mailgunResponse.getBody());
					}
					throw new Exception(errorMessage(request));
				}
				
				HttpStatus code = HttpStatus.OK;
				resp = MailResponse.builder()
						.status(code.value())
						.message(okMessage(request))
						.build();
				
				processResult = new ResponseEntity<MailResponse>(resp, code);
				log.info("Send email. Response -> {}", processResult);
				response.setResult(processResult);
			}catch(Exception e) {
				if(log.isDebugEnabled()) {
					e.printStackTrace();
				}
				HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
				resp = MailResponse.builder()
						.status(code.value())
						.message(e.getMessage())
						.build();
				
				processResult = new ResponseEntity<MailResponse>(resp, code);
				response.setErrorResult(processResult);
			}
		};
	}

}
