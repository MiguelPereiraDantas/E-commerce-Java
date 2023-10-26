package br.com.fourstore.data;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fourstore.model.Sale;

public interface DataHistoric extends MongoRepository<Sale, String> {
	
	List<Sale> findAllByCpf(String cpf);

}
