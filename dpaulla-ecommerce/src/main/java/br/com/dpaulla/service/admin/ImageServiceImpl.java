package br.com.dpaulla.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dpaulla.model.Imagem;
import br.com.dpaulla.repository.ImagemRepository;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	ImagemRepository imageRepository;

	@Override
	public void save(Imagem image) {
		imageRepository.save(image);
	}

	@Override
	public boolean checkEmpty() {
		if(imageRepository.count() < 1) {
			return true;
		}else {return false;}
	}

}
