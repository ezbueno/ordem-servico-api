package com.ezandro.ordemservico.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ezandro.ordemservico.api.dto.ComentarioDTO;
import com.ezandro.ordemservico.api.dto.ComentarioInputDTO;
import com.ezandro.ordemservico.api.entity.Comentario;
import com.ezandro.ordemservico.api.entity.OrdemServico;
import com.ezandro.ordemservico.api.exception.EntidadeNaoEncontradaException;
import com.ezandro.ordemservico.api.repository.OrdemServicoRepository;
import com.ezandro.ordemservico.api.service.OrdemServicoService;

@RestController
@RequestMapping(value = "ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada!"));
				
		return toCollectionDto(ordemServico.getComentarios());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInputDTO comentarioInputDTO) {
		Comentario comentario = ordemServicoService.adicionarComentario(ordemServicoId, comentarioInputDTO.getDescricao());
		return toModel(comentario);
		
	}
	
	private ComentarioDTO toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDTO.class);
	}
	
	private List<ComentarioDTO> toCollectionDto(List<Comentario> comentarios) {
		return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
	}

}
