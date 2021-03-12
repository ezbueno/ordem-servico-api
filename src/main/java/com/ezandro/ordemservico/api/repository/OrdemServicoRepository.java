package com.ezandro.ordemservico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezandro.ordemservico.api.entity.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
