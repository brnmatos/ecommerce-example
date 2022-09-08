package br.com.dpaulla.service;

import org.springframework.http.ResponseEntity;

import br.com.dpaulla.utils.MailWrapper;

public interface MailSenderService {
	ResponseEntity<String> send(String from, String to, String subject, Object mappedBody, String template) throws Exception;
	ResponseEntity<String> send(MailWrapper mailWrapper, Object mappedBody) throws Exception;

}
