package br.com.dpaulla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tb_produto")
public @Data class Produto{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="produtoId")
	private int produtoId;
		
	@Column(name="produtoDescricao")
	private String produtoDescricao;	
	
	@Column(name="produtoValor")
	private String produtoValor;
	
	@Column(name="produtoSaldo")
	private int produtoSaldo;
	
	@Column(name="produtoMarca")
	private int produtoMarca;
	
	@Column(name="produtoImagem")
	private String produtoImagem;

}
