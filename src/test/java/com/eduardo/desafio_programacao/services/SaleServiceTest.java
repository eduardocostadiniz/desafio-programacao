package com.eduardo.desafio_programacao.services;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.eduardo.desafio_programacao.models.SummarizedSalesDTO;
import com.eduardo.desafio_programacao.repositories.SalesRepository;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {

	private static final String FILENAME = "example_input.tab";

	@InjectMocks
	private SalesService salesService;

	@Mock
	private SalesRepository salesRepository;

	@Test
	@DisplayName("Deve ler os dados do arquivo e salvar no banco de dados")
	public void saveDataFromFile() throws IOException {
		// o arquivo deve estar na raiz do projeto
		File file = new File(FILENAME);
		byte[] fileBytes = Files.readAllBytes(file.toPath());
		final MockMultipartFile mockFile = new MockMultipartFile(FILENAME, fileBytes);

		SummarizedSalesDTO result = this.salesService.saveDataFromFile(mockFile);

		Assertions.assertTrue(result.totalGrossRevenue().compareTo(new BigDecimal(95.0)) == 0);
		Mockito.verify(salesRepository, Mockito.times(1)).saveAll(Mockito.anyList());

	}
	
	// TODO: Criar teste para quando não abrir o arquivo

}
