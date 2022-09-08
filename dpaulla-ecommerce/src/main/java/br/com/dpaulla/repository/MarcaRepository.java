package br.com.dpaulla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.dpaulla.model.Marca;

@Repository("marcaRepository")
public interface MarcaRepository extends JpaRepository<Marca, Integer>{

}
