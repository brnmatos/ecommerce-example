package br.com.dpaulla.model.examples;

import br.com.dpaulla.model.Produto;

public class ProductsExample {
	
	public Produto firstProduct() {
		Produto product = new Produto();
		product.setProdutoDescricao("Creme de Tratamento Mais Lisos");
		product.setProdutoImagem("assets/images/produtos/1.jpg");
		product.setProdutoMarca(1);
		product.setProdutoSaldo(3);
		product.setProdutoValor("10");
		return product;
	}
	
	public Produto secondProduct() {
		Produto product = new Produto();
		product.setProdutoDescricao("Shampoo Mais Lisos");
		product.setProdutoImagem("assets/images/produtos/2.jpg");
		product.setProdutoMarca(1);
		product.setProdutoSaldo(2);
		product.setProdutoValor("5");
		return product;
	}
	
	public Produto thirdProduct() {
		Produto product = new Produto();
		product.setProdutoDescricao("Condicionado Mais Lisos");
		product.setProdutoImagem("assets/images/produtos/3.jpg");
		product.setProdutoMarca(1);
		product.setProdutoSaldo(2);
		product.setProdutoValor("5");
		return product;
	}
	
	public Produto fourthProduct() {
		Produto product = new Produto();
		product.setProdutoDescricao("Kit Mais Lisos");
		product.setProdutoImagem("assets/images/produtos/4.jpg");
		product.setProdutoMarca(1);
		product.setProdutoSaldo(1);
		product.setProdutoValor("20");
		return product;
	}
	
	public Produto fifthProduct() {
		Produto product = new Produto();
		product.setProdutoDescricao("Shampoo Crespo Divino");
		product.setProdutoImagem("assets/images/produtos/5.jpg");
		product.setProdutoMarca(1);
		product.setProdutoSaldo(1);
		product.setProdutoValor("6");
		return product;
	}
	
	public Produto sixthProduct() {
		Produto product = new Produto();
		product.setProdutoDescricao("Teste Shampoo");
		product.setProdutoImagem("assets/images/produtos/6.jpg");
		product.setProdutoMarca(2);
		product.setProdutoSaldo(10);
		product.setProdutoValor("0.01");
		return product;
	}
	
	
	
	
	

}
