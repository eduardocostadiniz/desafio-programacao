package com.eduardo.desafio_programacao.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eduardo.desafio_programacao.models.Users;

@Service
public class TokenService {

	@Value("${api.algorythm.secret}")
	private String algorithmSecret;
	private static final String APPLICATION_ISSUER = "desafio-programacao";
	private static final String LOCAL_ZONE_OFFSET = "-03:00";	

	public String generateToken(Users user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(algorithmSecret);
			String token = JWT.create()
				.withIssuer(APPLICATION_ISSUER)
				.withSubject(user.getEmail())
				.withExpiresAt(this.getExpirationTime())
				.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao gerar o token");
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(algorithmSecret);
			return JWT.require(algorithm)
				.withIssuer(APPLICATION_ISSUER)
				.build()
				.verify(token)
				.getSubject();
		} catch (JWTVerificationException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao validar o token");
		}
	}

	private Instant getExpirationTime() {
		return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of(LOCAL_ZONE_OFFSET));
	}

}
