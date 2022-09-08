package br.com.dpaulla.repository;

import br.com.dpaulla.model.OrdersBuy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("compraRepository")
public interface CompraRepository extends JpaRepository<OrdersBuy, Integer>{

}
