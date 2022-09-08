package br.com.dpaulla.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.dpaulla.model.order;
import br.com.dpaulla.model.User;
import br.com.dpaulla.repository.TransacaoRepository;

@Service
public class TransacaoServiceImpl implements TransacaoService {
	@Autowired
	TransacaoRepository transacaoRepository;
	
	
	@Transactional
	public order saveAndReturn(order transacao) {
		transacaoRepository.save(transacao);
		
		return transacao;
	}


	@Override
	public List<order> findAllByUsuarioId(User user) {
		List<order> listTransacao = new ArrayList<order>();
		transacaoRepository.findAll().stream().forEach(action -> {
			if (action.getTransacaoUsuarioId() == user.getUserId()) {
				listTransacao.add(action);
			}
		});		
		
		return listTransacao;
	}
	
}