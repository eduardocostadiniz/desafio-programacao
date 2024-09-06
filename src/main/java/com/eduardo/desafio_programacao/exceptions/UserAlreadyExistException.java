package com.eduardo.desafio_programacao.exceptions;

public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 4694883876290769283L;

	public UserAlreadyExistException(String message) {
		super(message);
	}

}
