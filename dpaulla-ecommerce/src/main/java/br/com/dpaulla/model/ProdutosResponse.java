package br.com.dpaulla.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class ProdutosResponse implements Serializable{
	private static final long serialVersionUID = -6685152473156550743L;
	private int status;
	private Produto produtosResponse;
}
