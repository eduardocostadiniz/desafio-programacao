package com.eduardo.desafio_programacao.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eduardo.desafio_programacao.exceptions.JwtTokenManipulationException;

@ControllerAdvice
public class TokenExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(JwtTokenManipulationException.class)
	public ResponseEntity<String> treatTokenProblem(JwtTokenManipulationException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

}
