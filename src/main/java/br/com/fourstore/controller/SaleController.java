package br.com.fourstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fourstore.service.ServiceSale;
import br.com.fourstore.model.Credit;
import br.com.fourstore.model.Debit;
import br.com.fourstore.model.Money;
import br.com.fourstore.model.Pix;
import br.com.fourstore.model.Product;
import br.com.fourstore.model.Sale;

@RestController
@RequestMapping("/sale")
public class SaleController {
		
	@Autowired
	ServiceSale serviceSale;
	
	@GetMapping("/cart")
	public List<Product> getCart(){
		return serviceSale.getCart();
	}
	
	@GetMapping("/cart/{sku}")
	public Product getProductInCart(@PathVariable("sku") String sku) {
		return serviceSale.searchProductBySkuInCart(sku);
	}
	
	@PostMapping("/cart/add/{sku}/{theAmount}")
	public String postInCart(@PathVariable("sku") String sku, @PathVariable("theAmount") Integer theAmount) {
		return serviceSale.addProductToCart(sku, theAmount);
	}
	
	@DeleteMapping("/cart/delete/{sku}/{theAmount}")
	public String deleteInCart(@PathVariable("sku") String sku, @PathVariable("theAmount") Integer theAmount) {
		return serviceSale.removeProductFromCart(sku, theAmount);
	}
	
	@DeleteMapping("/cart/empty")
	public String deleteCart() {
		return serviceSale.emptyCart();
	}
	
	@GetMapping("/cart/total")
	public Double getTotal() {
		return serviceSale.purchaseTotal();
	}
	
	@GetMapping("/cart/checkcart")
	public List<Product> getCheckCart(){
		return serviceSale.checkCart();
	}
	
	@PostMapping("/cart/finalizar/credit")
	public Sale postHistoricCredit(@RequestBody Credit credit) {
		return serviceSale.checkout(1, credit);
	}
	
	@PostMapping("/cart/finalizar/debit")
	public Sale postHistoricDebit(@RequestBody Debit debit) {
		return serviceSale.checkout(2, debit);
	}
	
	@PostMapping("/cart/finalizar/money")
	public Sale postHistoricMoney(@RequestBody Money money) {
		return serviceSale.checkout(3, money);
	}
	
	@PostMapping("/cart/finalizar/pix")
	public Sale postHistoricPix(@RequestBody Pix pix) {
		return serviceSale.checkout(4, pix);
	}

}
