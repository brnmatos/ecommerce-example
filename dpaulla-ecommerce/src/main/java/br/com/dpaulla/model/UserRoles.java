/*
 * package br.com.dpaulla.model;
 * 
 * import java.io.Serializable;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.Table; import
 * lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import
 * lombok.RequiredArgsConstructor; import lombok.ToString;
 * 
 * @Builder
 * 
 * @ToString
 * 
 * @AllArgsConstructor
 * 
 * @RequiredArgsConstructor
 * 
 * @Entity
 * 
 * @Table(name = "user_roles") public @Data class UserRoles implements
 * Serializable { private static final long serialVersionUID =
 * -4499140210680610037L;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO)
 * 
 * @Column(name = "user_x_roles_id") private int userXroleId;
 * 
 * @Column(name = "users_user_id") private Long users_user_id;
 * 
 * @Column(name = "roles_role_id") private Long roles_role_id;
 * 
 * }
 */