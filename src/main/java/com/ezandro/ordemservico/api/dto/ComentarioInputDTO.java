package com.ezandro.ordemservico.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioInputDTO {
	
	@NotBlank
	private String descricao;
	
}
