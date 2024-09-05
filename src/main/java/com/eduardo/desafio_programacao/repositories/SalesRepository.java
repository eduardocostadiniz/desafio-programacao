package com.eduardo.desafio_programacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.desafio_programacao.models.Sales;


public interface SalesRepository extends JpaRepository<Sales, Long> {

}
