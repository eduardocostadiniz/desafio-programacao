package com.eduardo.desafio_programacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardo.desafio_programacao.exceptions.UserAlreadyExistException;
import com.eduardo.desafio_programacao.models.LoginResponseDTO;
import com.eduardo.desafio_programacao.models.UserRequestDTO;
import com.eduardo.desafio_programacao.models.Users;
import com.eduardo.desafio_programacao.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	public void create(UserRequestDTO user) {
		if (this.usersRepository.findByEmail(user.email()) != null) {
			throw new UserAlreadyExistException("Usuário já existe");
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
		Users createdUser = new Users();
		createdUser.setEmail(user.email());
		createdUser.setPassword(encryptedPassword);
		this.usersRepository.save(createdUser);
	}

	public LoginResponseDTO login(UserRequestDTO user) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.email(),
				user.password());
		Authentication auth = this.authManager.authenticate(authToken);
		String newToken = this.tokenService.generateToken((Users) auth.getPrincipal());
		return new LoginResponseDTO(newToken);
	}

}
