package br.com.fourstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourstore.data.DataHistoric;
import br.com.fourstore.model.Sale;

@Service
public class ServiceHistoric {
	
	@Autowired
	DataHistoric dataHistoric;
	
	//Salvar no historico (bd)
	public Sale saveToHistory(Sale sale) {
		try {
			return dataHistoric.save(sale);
		} catch (Exception e) {
			return null;
		}
	}
	
	//Listar
	public List<Sale> historyList() {
		return dataHistoric.findAll();
	}
	
	//Listar todas as vendas com o msm cpf
	public List<Sale> getHistoryWithCpf(String cpf){
		return dataHistoric.findAllByCpf(cpf);
	}
	
	//Pegar por id
	public Sale getHistoricId(String id) {
		for(int x=0; x<historyList().size(); x++) {
			Sale sale = historyList().get(x);
			if(id.equals(sale.getId())){
				return sale;
			}
		}
		return null;
	}
}