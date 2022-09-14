package br.com.dpaulla.mail.client.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

import br.com.dpaulla.mail.client.MailClient;
import br.com.dpaulla.model.response.MailResponse;
import br.com.dpaulla.model.wrapper.CadastroWrapper;

@Service
public class MailClientImpl implements MailClient {

	private static final Class<MailResponse> DEFAULT_RESPONSE_TYPE = MailResponse.class;
	
	private String serverBaseUrl;
	
	private AsyncRestTemplate client = new AsyncRestTemplate();
	
	private static Logger log = LoggerFactory.getLogger(MailClientImpl.class);
	
	public MailClientImpl() {
		this(MAIL_SERVER_BASE_URL);
	}
	
	public MailClientImpl(String serverBaseUrl) {
		this.setServerBaseUrl(serverBaseUrl);
	}
	public String getServerBaseUrl() {
		return serverBaseUrl;
	}

	public void setServerBaseUrl(String serverBaseUrl) {
		this.serverBaseUrl = serverBaseUrl;
	}

	@Override
	public Future<ResponseEntity<MailResponse>> sendCadastroEmail(CadastroWrapper emailCadastroWrapper) {
		log.info("Fire alertsWrapper request");
		return emailCadastroResource(emailCadastroWrapper, getCadastroUrl());
	}
	
	private String getCadastroUrl() {
		return serverBaseUrl + MailClient.URL_EMAIL_CADASTRO;
	}

	private Future<ResponseEntity<MailResponse>> emailCadastroResource(CadastroWrapper emailCadastroWrapper, String emailCadastroUrl) {
		log.info("Fire antennaResource request");
		return exchange(emailCadastroUrl, HttpMethod.POST, emailCadastroWrapper);
	}
	
	private Future<ResponseEntity<MailResponse>> exchange(String url, HttpMethod method, CadastroWrapper emailCadastroWrapper){
		log.info("Fire exchange request");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CadastroWrapper> httpEntity = new HttpEntity<CadastroWrapper>(emailCadastroWrapper, headers);
		log.info("return client request");
		log.info("httpEntity: {}", httpEntity.getBody());
		return client.exchange(url, method, httpEntity, DEFAULT_RESPONSE_TYPE);
	}
	
}
