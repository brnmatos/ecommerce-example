package br.com.dpaulla.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class MailResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int status;
	private String message;
}
