package br.com.dpaulla.mail.client;

import java.util.concurrent.Future;
import org.springframework.http.ResponseEntity;

import br.com.dpaulla.model.response.MailResponse;
import br.com.dpaulla.model.wrapper.CadastroWrapper;

public interface MailClient {
	
	public final String MAIL_SERVER_BASE_URL = "http://localhost:9090";
	public final String URL_EMAIL_CADASTRO = "/mail/cadastro";
	
	Future<ResponseEntity<MailResponse>> sendCadastroEmail(CadastroWrapper alerts);
	
}
