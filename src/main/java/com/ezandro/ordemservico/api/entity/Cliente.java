package com.ezandro.ordemservico.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
}
