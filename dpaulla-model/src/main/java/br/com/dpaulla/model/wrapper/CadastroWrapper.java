package br.com.dpaulla.model.wrapper;

import br.com.dpaulla.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public @Data class CadastroWrapper {
	private User userCadastrado;
}
