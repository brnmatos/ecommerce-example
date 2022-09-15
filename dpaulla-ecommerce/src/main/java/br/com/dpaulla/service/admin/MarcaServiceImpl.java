package br.com.dpaulla.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dpaulla.model.Marca;
import br.com.dpaulla.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService{

	@Autowired
	MarcaRepository marcaRepository;
	
	@Override
	public void save(Marca marca) {
		marcaRepository.save(marca);
	}

	@Override
	public boolean checkEmpty() {
		if(marcaRepository.count() < 1) {
			return true;
		}else {return false;}
	}

}
