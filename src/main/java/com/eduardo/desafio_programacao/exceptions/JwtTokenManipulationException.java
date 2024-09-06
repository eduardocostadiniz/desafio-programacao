package com.eduardo.desafio_programacao.exceptions;

public class JwtTokenManipulationException extends RuntimeException {

	private static final long serialVersionUID = -7877688527785526475L;

	public JwtTokenManipulationException(String message) {
		super(message);
	}
}
