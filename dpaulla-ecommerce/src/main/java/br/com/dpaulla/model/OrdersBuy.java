package br.com.dpaulla.model;

import java.io.Serializable;

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
@Table(name = "tb_compra")
public @Data class OrdersBuy implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="compraId")
	private int compraId;
	
	@Column(name="compraUser")
	private long compraUsuarioId;

	@Column(name="compraProdutoId")
	private int compraProdutoId;
	
	@Column(name="compraTransacao")
	private int compraTransacao;
	
	@Column(name="compraProdutoDescricao")
	private String compraProdutoDescricao;
	
	@Column(name="compraProdutoValor")
	private String compraProdutoValor;

	@Column(name="compraProdutoQuantidade")
	private int compraProdutoQuantidade;
	
	@Column(name="compraProdutoImagemPrincipal")
	private String compraProdutoImagemPrincipal;
	
	@Column(name="compraStatus")
	private String compraStatus;
	
	
}
