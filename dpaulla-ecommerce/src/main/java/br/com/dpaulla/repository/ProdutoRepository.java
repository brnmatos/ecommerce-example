package br.com.dpaulla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dpaulla.model.Produto;

@Repository("produtoRepository")
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	List<Produto> findByprodutoMarca(int marcaId);
	/*
	 * @Query("insert into tb_transacao (transacao_cartao_nome, transacao_cartao_n)values "
	 * ) public static String insertAndReturn() {
	 * 
	 * return ""; }
	 */
	
	
	
}
