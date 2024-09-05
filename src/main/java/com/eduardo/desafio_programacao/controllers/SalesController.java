package com.eduardo.desafio_programacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eduardo.desafio_programacao.models.SummarizedSalesDTO;
import com.eduardo.desafio_programacao.services.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private SalesService salesService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SummarizedSalesDTO> importSales(@RequestParam("arquivo") MultipartFile file) {
		SummarizedSalesDTO resume = salesService.saveDataFromFile(file);
		return ResponseEntity.ok().body(resume);
	}

}
