package br.com.fourstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fourstore.model.Sale;
import br.com.fourstore.service.ServiceHistoric;

@RestController
@RequestMapping("/historic")
public class HistoricController {
	
	@Autowired
	ServiceHistoric serviceHistoric;
	
	@GetMapping("/list")
	public List<Sale> getList(){
		return serviceHistoric.historyList();
	}
	
	@GetMapping("/list/{cpf}")
	public List<Sale> getListCpf(@PathVariable(value = "cpf") String cpf){
		return serviceHistoric.getHistoryWithCpf(cpf);
	}
	
	@GetMapping("/list/{id}")
	public Sale getSaleId(@PathVariable(value = "id") String id) {
		return serviceHistoric.getHistoricId(id);
	}
}
