package com.ezandro.ordemservico.api.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezandro.ordemservico.api.entity.Cliente;
import com.ezandro.ordemservico.api.entity.OrdemServico;
import com.ezandro.ordemservico.api.entity.StatusOrdemServico;
import com.ezandro.ordemservico.api.exception.NegocioException;
import com.ezandro.ordemservico.api.repository.ClienteRepository;
import com.ezandro.ordemservico.api.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
}
