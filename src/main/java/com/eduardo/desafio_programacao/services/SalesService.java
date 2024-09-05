package com.eduardo.desafio_programacao.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eduardo.desafio_programacao.models.Sales;
import com.eduardo.desafio_programacao.models.SummarizedSalesDTO;
import com.eduardo.desafio_programacao.repositories.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository salesRepository;

	public List<Sales> listSales() {
		return salesRepository.findAll();
	}

	private List<Sales> readDataFromFile(MultipartFile file) {
		try {
			InputStream input = file.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(inputReader);
			List<Sales> sales = reader.lines().skip(1).map((line) -> this.parseLine(line.split("\t"))).toList();
			return sales;

		} catch (IOException ex) {
			ex.printStackTrace();
			// TODO: melhorar a tipagem da exceção
			throw new RuntimeException("Erro ao transformar o arquivo!");
		}
	}

	private Sales parseLine(String[] saleLine) {
		Sales sale = new Sales();
		sale.setPurchaserName(saleLine[0]);
		sale.setItemDescription(saleLine[1]);
		sale.setItemPrice(new BigDecimal(saleLine[2]));
		sale.setPurchaseCount(Integer.parseInt(saleLine[3]));
		sale.setMerchantAddress(saleLine[4]);
		sale.setMerchantName(saleLine[5]);
		return sale;
	}

	private SummarizedSalesDTO summarizeInsertedData(List<Sales> sales) {
		return new SummarizedSalesDTO(
			sales.stream()
				.map(sale -> sale.getItemPrice().multiply(new BigDecimal(sale.getPurchaseCount())))
				.reduce(BigDecimal.ZERO, (total, element) -> total.add(element))
		);
	}

	public SummarizedSalesDTO saveDataFromFile(MultipartFile file) {
		List<Sales> sales = this.readDataFromFile(file);
		salesRepository.saveAll(sales);	
		return this.summarizeInsertedData(sales);
	}

}
