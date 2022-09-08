package br.com.dpaulla.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class Role {

	private Long roleId;
	private String name;
	private Set<User> users;

}
