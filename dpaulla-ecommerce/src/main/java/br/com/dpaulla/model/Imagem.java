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
@Table(name = "tb_imagem")
public @Data class Imagem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="imagemId")
	private int imagemId;
	
	@Column(name="produtoId")
	private int produtoId;
		
	@Column(name="imagemTipo") //1 = FRONTAL, 2 = LATERAL, 3 = SEI LÁ, 4 = MODELO
	private String imagemTipo;	
	
	@Column(name="imagemDiretorio")
	private String imagemDiretorio;
	
}
