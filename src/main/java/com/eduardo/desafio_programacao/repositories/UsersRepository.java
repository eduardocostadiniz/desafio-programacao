package com.eduardo.desafio_programacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.eduardo.desafio_programacao.models.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	
	UserDetails findByEmail(String email);

}
