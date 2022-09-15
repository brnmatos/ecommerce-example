package br.com.dpaulla.service.admin;

import br.com.dpaulla.model.Imagem;

public interface ImageService {
	
	public void save(Imagem image);
	public boolean checkEmpty();

}
