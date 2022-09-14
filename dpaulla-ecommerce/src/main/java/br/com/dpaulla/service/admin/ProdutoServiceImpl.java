package br.com.dpaulla.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.dpaulla.model.Produto;
import br.com.dpaulla.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Override
	public void save(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public boolean checkEmpty() {
		if(produtoRepository.count() < 1) {
			return true;
		}else {return false;}
	}

}
