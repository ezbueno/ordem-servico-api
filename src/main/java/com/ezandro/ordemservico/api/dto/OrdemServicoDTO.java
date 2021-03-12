package com.ezandro.ordemservico.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ezandro.ordemservico.api.entity.StatusOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoDTO {
	
	private Long id;
	private String nomeCliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
}
