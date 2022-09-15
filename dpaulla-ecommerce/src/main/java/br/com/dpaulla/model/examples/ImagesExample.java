package br.com.dpaulla.model.examples;

import br.com.dpaulla.model.Imagem;

public class ImagesExample {
	
	public Imagem firstImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1.jpg");
		image.setImagemTipo("Frontal");
		image.setProdutoId(1);
		return image;
	}
	
	public Imagem secondImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1-2.jpg");
		image.setImagemTipo("Lateral");
		image.setProdutoId(1);
		return image;
	}
	
	public Imagem thirdImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1-3.jpg");
		image.setImagemTipo("Superior");
		image.setProdutoId(1);
		return image;
	}
	
	public Imagem fourthImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1-4.jpg");
		image.setImagemTipo("Modelo");
		image.setProdutoId(1);
		return image;
	}
	
	public Imagem fifthImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1.jpg");
		image.setImagemTipo("Frontal");
		image.setProdutoId(6);
		return image;
	}
	
	public Imagem sixthImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1-2.jpg");
		image.setImagemTipo("Lateral");
		image.setProdutoId(2);
		return image;
	}
	
	public Imagem seventhImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("assets/images/produtos/1-3.jpg");
		image.setImagemTipo("Superior");
		image.setProdutoId(6);
		return image;
	}
	
	public Imagem eighthImage() {
		Imagem image = new Imagem();
		image.setImagemDiretorio("aassets/images/produtos/1-4.jpg");
		image.setImagemTipo("Modelo");
		image.setProdutoId(6);
		return image;
	}

}
