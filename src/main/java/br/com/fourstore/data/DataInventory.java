package br.com.fourstore.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fourstore.model.Product;

public interface DataInventory extends MongoRepository<Product, String>{
	
	Product findBySku(String sku);
}
