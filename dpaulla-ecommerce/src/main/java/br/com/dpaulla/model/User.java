package br.com.dpaulla.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "user")
public @Data class User implements Serializable{
		private static final long serialVersionUID = -4499140210680610037L;

		@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long userId;

	 	private String nome;
	 	
	 	private String sobrenome;
	 	
	 	private String cpf;
	 	
	 	private String datanascimento;
	 	
	 	private String telefone;
	 	
	 	private String celular;
	 	
	 	private String cep;
	 	
	 	private String logradouro;
	 	
	 	private String logradouronumero;
	 	
	 	private String complemento;
	 	
	 	private String bairro;
	 	
	 	private String cidade;
	 	
	 	private String estado;
	 	
	    private String username;

	    private String password;

	    @Transient
	    private String passwordConfirm;
	    
	    @Transient
	    private String usernameConfirm;
	    
	    @OneToMany
	    private Set<Role> roles;
	    
}