package br.com.dpaulla.model.examples;

import br.com.dpaulla.model.Marca;

public class BrandsExample {

	public Marca firstBrand() {
		Marca marca = new Marca();
		marca.setMarcaDescricao("Skala");
		marca.setMarcaLogo("scala.jpg");
		marca.setMarcaNivel("Basico");
		return marca;
	}
	
	public Marca secondBrand() {
		Marca marca = new Marca();
		marca.setMarcaDescricao("Soul Power");
		marca.setMarcaLogo("soulpouwer.jpg");
		marca.setMarcaNivel("Bom");
		return marca;
	}
	
	public Marca thirdBrand() {
		Marca marca = new Marca();
		marca.setMarcaDescricao("Embeleze");
		marca.setMarcaLogo("embeleze.jpg");
		marca.setMarcaNivel("Otimo");
		return marca;
	}
	
	public Marca fourthBrand() {
		Marca marca = new Marca();
		marca.setMarcaDescricao("Salon Line");
		marca.setMarcaLogo("salonline.jpg");
		marca.setMarcaNivel("Otimo");
		return marca;
	}
	
}
