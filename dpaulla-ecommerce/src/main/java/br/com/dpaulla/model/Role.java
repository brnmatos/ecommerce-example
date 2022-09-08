package br.com.dpaulla.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "role")
public @Data class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	private String name;

    @ManyToOne(targetEntity = User.class)
	private Set<User> users;
	
}