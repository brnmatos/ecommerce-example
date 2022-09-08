package br.com.dpaulla.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.dpaulla.model.order;

@Repository("transacaoRepository")
public interface TransacaoRepository extends JpaRepository<order, Integer> {
	
}