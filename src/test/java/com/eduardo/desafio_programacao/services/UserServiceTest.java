package com.eduardo.desafio_programacao.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import com.eduardo.desafio_programacao.models.UserRequestDTO;
import com.eduardo.desafio_programacao.repositories.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UsersService userService;

	@Mock
	private UsersRepository usersRepository;

	@Mock
	private AuthenticationManager authManager;

	@Test
	@DisplayName("Deve salvar o usuário na base de dados")
	public void saveUser() {

		UserRequestDTO user = new UserRequestDTO("user@teste.com", "123");
		this.userService.create(user);

		Mockito.verify(this.usersRepository, Mockito.times(1)).findByEmail(user.email());
		Mockito.verify(this.usersRepository, Mockito.times(1)).save(Mockito.any());

	}

}
