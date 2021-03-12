package com.ezandro.ordemservico.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ezandro.ordemservico.api.dto.OrdemServicoDTO;
import com.ezandro.ordemservico.api.dto.OrdemServicoInputDTO;
import com.ezandro.ordemservico.api.entity.OrdemServico;
import com.ezandro.ordemservico.api.repository.OrdemServicoRepository;
import com.ezandro.ordemservico.api.service.OrdemServicoService;

@RestController
@RequestMapping(value = "/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServicoInputDTO ordemServicoInputDTO) {
		OrdemServico ordemServico = toEntity(ordemServicoInputDTO);
		return ordemServicoService.criar(ordemServico);
	}
	
	@GetMapping
	public List<OrdemServicoDTO> listar() {
		return toCollectionDto(ordemServicoRepository.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long id) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);
		
		if(ordemServico.isPresent()) {
			OrdemServicoDTO dto = toModel(ordemServico.get());
			return ResponseEntity.ok(dto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(value = "/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) {
		ordemServicoService.finalizar(ordemServicoId);
	}
	
	private OrdemServicoDTO toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
	}
	
	private List<OrdemServicoDTO> toCollectionDto(List<OrdemServico> ordemServico) {
		return ordemServico.stream().map(os -> toModel(os)).collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInputDTO ordemServicoInputDTO) {
		return modelMapper.map(ordemServicoInputDTO, OrdemServico.class);
	}
}
