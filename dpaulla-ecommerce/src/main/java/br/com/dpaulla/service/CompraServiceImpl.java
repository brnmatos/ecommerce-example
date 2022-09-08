package br.com.dpaulla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.dpaulla.model.OrdersBuy;
import br.com.dpaulla.repository.CompraRepository;

@Service
public class CompraServiceImpl implements CompraService {
	
	@Autowired
	CompraRepository comprasRepository;

	@Override
	public void save(OrdersBuy compras) {
		comprasRepository.save(compras);
	}
}