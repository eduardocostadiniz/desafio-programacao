package com.eduardo.desafio_programacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.desafio_programacao.models.LoginResponseDTO;
import com.eduardo.desafio_programacao.models.UserRequestDTO;
import com.eduardo.desafio_programacao.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody UserRequestDTO user) {
		this.usersService.create(user);
		return ResponseEntity.ok().body("Usuário criado: " + user.email());
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody UserRequestDTO user) {
		this.usersService.login(user);
		return ResponseEntity.ok(this.usersService.login(user));
	}

}
