package br.com.fourstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourstore.data.DataInventory;
import br.com.fourstore.model.Product;

@Service
public class ServiceProduct {
	
	//Banco de dados dos produtos
	@Autowired
	private DataInventory dataInventory;
	
	//lista de todos os produtos
	public List<Product> productList(){
		return dataInventory.findAll();
	}
	
	//Procurar produto por SKU
	public Product searchProductBySku(String sku) {
		return dataInventory.findBySku(sku);
	}
	
	//Registrar o produto
	public String registerProduct(Product product) {
		if(product.getSku() == null || product.getDescription() == null || product.getTheAmount() == null || product.getPrice() == null) {
			return "Informacoes inseridas invalidas";
		} else {
			if(product.getType() == null || product.getColor() == null || product.getDepartment() == null || product.getSize() == null || product.getCategory() == null) {
				return "SKU Inexistente";
			} else {
				Product productSku = searchProductBySku(product.getSku());
				if(productSku == null && product.getTheAmount() > 0) {
					try {
						dataInventory.save(product);
						return "Produto cadastrado com sucesso";
					} catch (Exception e) {
						return "Cadastro do produto falhou" + e.getMessage();
					}
				} else {
					return "SKU ja existente ou Quantidade Invalida";
				}
			}
		}
	}
	
	//Editar o produto
	public String editProduct(String sku, Product product) {
		Product searchProductBySku = searchProductBySku(sku);
		Integer theAmount = 0;

		if(searchProductBySku != null) {
			product.setSku(sku);
			if(product.getDescription() == null) {
				product.setDescription(searchProductBySku.getDescription());
			}
			
			if(product.getTheAmount() == null) {
				product.setTheAmount(theAmount);
			}
			
			if(product.getPrice() == null) {
				product.setPrice(searchProductBySku.getPrice());
			}
			
			if(product.getTheAmount() != 0) {
				theAmount = searchProductBySku.getTheAmount() + (product.getTheAmount());
				if(theAmount < 0) {
					return "Quantidade Invalida";
				} else {
					product.setTheAmount(theAmount);
				}
			}
			
			try {
				Product updatedProduct = dataInventory.findById(searchProductBySku.getId()).orElse(null);
				updatedProduct.setDescription(product.getDescription());
				updatedProduct.setPrice(product.getPrice());
				updatedProduct.setSku(product.getSku());
				updatedProduct.setTheAmount(product.getTheAmount());
				dataInventory.save(updatedProduct);
				return "Produto editado com sucesso";
			} catch (Exception e) {
				return "Edição do produto falhou" + e.getMessage();
			}
		} else {
			return "SKU nao encontrado";
		}		
	}
	
	//Deletar o produto pelo SKU
	public String deleteProduct(String sku) {
		Product product = searchProductBySku(sku);
		if(product != null) {
			try {
				dataInventory.deleteById(product.getId());
				return "Produto deletado com sucesso";
			} catch (Exception e) {
				return "Falha ao deletar o produto";
			}			
		} else {
			return "SKU nao invalido";
		}
	}	
}
