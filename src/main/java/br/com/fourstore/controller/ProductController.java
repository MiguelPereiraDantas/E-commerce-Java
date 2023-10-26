package br.com.fourstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fourstore.model.Product;
import br.com.fourstore.service.ServiceProduct;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ServiceProduct serviceProduct;
	
	@PostMapping("/addlist")
	public String postProducts() {
		Product p1 = new Product("CAM1MA1RCA", "Camisa Basica Preta", 10, 10.0);
		Product p2 = new Product("CAT2FE2RCL", "Camiseta Fem. Branca Lascoste", 20, 20.0);
		Product p3 = new Product("CAL3IN3RES", "Calça Azul do Homem-Aranha", 30, 30.0);
		Product p4 = new Product("SHO4MA4RSO", "Short Vermelho da Polo", 40, 40.0);
		Product p5 = new Product("BLU5FE5RCA", "Blusa Amarela Fem.", 50, 50.0);
		
		serviceProduct.registerProduct(p1);
		serviceProduct.registerProduct(p2);
		serviceProduct.registerProduct(p3);
		serviceProduct.registerProduct(p4);
		serviceProduct.registerProduct(p5);
		return "Produtos Cadastrados";
	}
	
	@GetMapping("/list")
	public List<Product> getProductsList(){
		return serviceProduct.productList();
	}
	
	@GetMapping("/procurar/{sku}")
	public Product getProductbySku(@PathVariable(value= "sku") String sku) {
		return serviceProduct.searchProductBySku(sku);
	}
	
	@PostMapping("/cadastrar")
	public String postProduct(@RequestBody Product product) {
		return serviceProduct.registerProduct(product);
	}
	
	@PatchMapping("/editar/{sku}")
	public String patchProduct(@RequestBody Product product, @PathVariable(value= "sku") String sku) {
		return serviceProduct.editProduct(sku, product);
	}
	
	@DeleteMapping("/deletar/{sku}")
	public String deleteProduct(@PathVariable(value= "sku") String sku) {
		return serviceProduct.deleteProduct(sku);
	}

}
