package com.ezandro.ordemservico.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezandro.ordemservico.api.entity.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping(value = "/clientes")
	public List<Cliente> listar() {
		Cliente c1 = new Cliente();
		c1.setNome("Ezandro");
		c1.setEmail("ezandro@teste.com");
		c1.setTelefone("11 99999-9999");
		
		Cliente c2 = new Cliente();
		c2.setNome("Nayara");
		c2.setEmail("nayara@teste.com");
		c2.setTelefone("11 90000-9999");
		
		return Arrays.asList(c1, c2);
	}
}
