package br.com.dpaulla.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.dpaulla.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
	List<Role> findByName(String name);
}