package br.com.dpaulla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.dpaulla.model.Imagem;

@Repository("imagemRepository")
public interface ImagemRepository extends JpaRepository<Imagem, Integer>{
	List<Imagem> findImagemByprodutoId(int produtoId);
}
