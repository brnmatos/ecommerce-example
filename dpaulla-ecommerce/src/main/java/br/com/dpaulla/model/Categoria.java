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
@Table(name = "tb_categoria")
public @Data class Categoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="categoriaId")
	private int categoriaId;
		
	@Column(name="categoriaDescricao")
	private String categoriaDescricao;	

}
