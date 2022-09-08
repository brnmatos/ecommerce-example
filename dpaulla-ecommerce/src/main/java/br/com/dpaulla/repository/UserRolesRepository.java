/*
 * package br.com.dpaulla.repository;
 * 
 * import br.com.dpaulla.model.UserRoles;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.data.jpa.repository.Query; import
 * org.springframework.stereotype.Repository;
 * 
 * @Repository("userRolesRepository") public interface UserRolesRepository
 * extends JpaRepository<UserRoles, Long> {
 * 
 * @Query(value = "SELECT * FROM user_roles WHERE users_user_id = ?1",
 * nativeQuery = true) List<UserRoles> findAllByUser(long userId);
 * 
 * }
 */