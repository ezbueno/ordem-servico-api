package com.ezandro.ordemservico.api.exception;

@SuppressWarnings("serial")
public class EntidadeNaoEncontradaException extends NegocioException {
	
	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
	
}
