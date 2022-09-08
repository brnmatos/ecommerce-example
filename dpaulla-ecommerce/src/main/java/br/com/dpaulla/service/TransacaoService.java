package br.com.dpaulla.service;

import java.util.List;

import br.com.dpaulla.model.order;
import br.com.dpaulla.model.User;

public interface TransacaoService {

	public order saveAndReturn(order transacao);
	public List<order> findAllByUsuarioId(User user);
	
}