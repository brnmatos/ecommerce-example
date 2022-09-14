package br.com.dpaulla.service.admin;

import br.com.dpaulla.model.Produto;

public interface ProdutoService {
	
	public void save(Produto produto);
	public boolean checkEmpty();

}
