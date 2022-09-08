package br.com.crb.mercury.mail.server.service;

import org.springframework.http.ResponseEntity;

import br.com.crb.mercury.mail.server.utils.MailWrapper;

public interface MailSenderService {

	ResponseEntity<String> send(String from, String to, String subject, Object mappedBody, String template) throws Exception;
	ResponseEntity<String> send(MailWrapper mailWrapper, Object mappedBody) throws Exception;
}
