package com.ezandro.ordemservico.api.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {
	
	private Long id;
	private String descricao;
	private OffsetDateTime dataEnvio;
	
}
