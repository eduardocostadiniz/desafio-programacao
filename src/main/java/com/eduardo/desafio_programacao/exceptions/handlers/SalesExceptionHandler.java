package com.eduardo.desafio_programacao.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eduardo.desafio_programacao.exceptions.TransformDataException;

@ControllerAdvice
public class SalesExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransformDataException.class)
	public ResponseEntity<String> treatFileWithProblems(TransformDataException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

}
