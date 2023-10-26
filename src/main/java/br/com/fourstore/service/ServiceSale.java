package br.com.fourstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourstore.enums.TypeOfSaleEnum;
import br.com.fourstore.model.Credit;
import br.com.fourstore.model.Debit;
import br.com.fourstore.model.Money;
import br.com.fourstore.model.Pix;
import br.com.fourstore.model.Product;
import br.com.fourstore.model.Sale;

@Service
public class ServiceSale {
	
	@Autowired
	ServiceProduct serviceProduct;
	
	@Autowired
	ServiceHistoric serviceHistoric;
	
	private static List<Product> cart = new ArrayList<Product>();
	
	//Listar produtos no carrinho
	public List<Product> getCart(){
		return cart;
	}
	
	//Procurar produto por SKU no carrinho
	public Product searchProductBySkuInCart(String sku) {
		for(int x=0; x<getCart().size(); x++) {
			Product product = getCart().get(x);
			if(sku.equals(product.getSku())) {
				return product;
			}
		}
		return null;
	}
	
	//Adicionar produto no carrinho atraves do SKU
	public String addProductToCart(String sku, Integer theAmount) {
		Product product = serviceProduct.searchProductBySku(sku);
		
		if(product == null) {
			return "SKU inexistente";
		} else if(product.getTheAmount() <= 0) {
			return "Sem essa quantidade no estoque";
		} else if(theAmount <= 0) {
			return "Por favor digite uma quantidade valida";
		} else {
				product.setTheAmount(theAmount);
				Product cartProduct = searchProductBySkuInCart(sku);
				if(cartProduct == null) {
					cart.add(product);
					return "Produto adicionado com sucesso";
				} else {
					cartProduct.setTheAmount(cartProduct.getTheAmount() + theAmount);
					if(editProductInCart(cartProduct) == 0) {
						return "Produto adicionado com sucesso";
					} else {
						return "Falha ao adicionar o produto";
					}
					
				}		
		}
	}
	
	//Retirar produto do carrinho
	public String removeProductFromCart(String sku, Integer theAmount) {
		Product product = searchProductBySkuInCart(sku);
		
		if(product == null) {
			return "SKU inexistente";
		} else if(product.getTheAmount() < theAmount) {
			return "Nao existe essa quantidade no carrinho";
		} else if(theAmount <= 0) {
			return "Por favor digite uma quantidade valida";
		} else {
			try {
				product.setTheAmount(product.getTheAmount() - theAmount);
				editProductInCart(product);
				return "Produto retitado do carrinho";			
			} catch (Exception e) {
				return "Falha ao retirar o produto do carrinho";
			}
		}
	}
	
	//editar produto no carrinho
	private Integer editProductInCart(Product product) {
		Integer index = -1;
		for(int x=0; x<getCart().size(); x++) {
    		Product prouctSku = getCart().get(x);
    		if(product.getSku().equals(prouctSku.getSku())) {
    			index = x;
    		}
    	}
		switch(index) {
		case -1:
			return 1;//"SKU nao encontrado";
			
		default:
			if(cart.set(index, product) != null) {
				checkZeroProducts();
				return 0;//"Produto editado com sucesso";
			} else {
				return 1;//"Edição do produto falhou";
			}
		}
	}
	
	//Esvaziar o carrinho
	public String emptyCart() {
		for(int x=0; x<getCart().size(); x++) {
    		Product product = getCart().get(x);
    		removeProductFromCart(product.getSku(), product.getTheAmount());
    	}
		return "Compra Cancelada...Carrinho Esvaziado";
	}
	
	//total das compras
	public Double purchaseTotal() {
		Double total = 0.0;
		for(int x=0; x<getCart().size(); x++) {
    		total = total + (getCart().get(x).getPrice() * getCart().get(x).getTheAmount());
    	}
		return total;
	}
	
	//verificar os produtos que estão zerados e tirar eles
	private void checkZeroProducts() {
		for(int x=0; x<getCart().size(); x++) {
			Product product = getCart().get(x);
			if(product.getTheAmount() <=0 ) {
				cart.remove(product);
			}
		}
	}
	
	//Verificar produtos no estoque da loja antes de finalizar a compra
	public List<Product> checkCart() {
		List<Product> insufficientProducts = new ArrayList<Product>();
		
		for(int x=0; x<getCart().size(); x++) {
			Product productInCart = getCart().get(x);
			Product productInInventory = serviceProduct.searchProductBySku(productInCart.getSku());
			if(productInInventory == null || productInInventory.getTheAmount() < productInCart.getTheAmount()) {
				insufficientProducts.add(productInInventory);
				removeProductFromCart(productInCart.getSku(), productInCart.getTheAmount());
			}
		}
		return insufficientProducts;
	}
	
	//Retirar produto do iventario		
	private void removeProductsFromInventory() {
		Integer theAmount;
		for(int x=0; x<getCart().size(); x++) {
			Product productInCart = getCart().get(x);
			theAmount = (-1) * productInCart.getTheAmount();
			Product updatedProduct = new Product(productInCart.getSku(), productInCart.getDescription(), theAmount, productInCart.getPrice());
			serviceProduct.editProduct(updatedProduct.getSku(), updatedProduct);
		}
	}
	
	//Finalizar compra
	public Sale checkout(Integer typeOfSale, Sale sale) {
		if(getCart().size() <= 0 || getCart() == null || purchaseTotal() == null || purchaseTotal() <= 0) {
			return null;
		} else {
			sale.setTypeOfSale(TypeOfSaleEnum.getTypeOfSaleEnum(typeOfSale));
			sale.setTotal(purchaseTotal());
			sale.setCart(getCart());
			
			if(sale.getTypeOfSale() == TypeOfSaleEnum.CREDITO) {
				Credit credit = (Credit) sale;
				if(credit.getCardNumber() == null || credit.getCardNumber() == "" ||
				   credit.getExpirationDate() == null || credit.getExpirationDate() == "" ||
				   credit.getSecurityCode() == null || credit.getSecurityCode() < 0 ||
				   credit.getInstallments() == null || credit.getInstallments() < 0) {
					return null;
				} else {
					if(serviceHistoric.saveToHistory(credit) == null) {
						return null;
					} else {
						removeProductsFromInventory();
						cart.clear();
						return serviceHistoric.getHistoricId(credit.getId());
					}
				}
			} else if(sale.getTypeOfSale() == TypeOfSaleEnum.DEBITO) {
				Debit debit = (Debit) sale;
				if(debit.getCardNumber() == null || debit.getCardNumber() == "" ||
				   debit.getExpirationDate() == null || debit.getExpirationDate() == "" ||
				   debit.getSecurityCode() == null || debit.getSecurityCode() < 0) {
					return null;
				} else {
					if(serviceHistoric.saveToHistory(debit) == null) {
						return null;
					} else {
						removeProductsFromInventory();
						cart.clear();
						return serviceHistoric.getHistoricId(debit.getId());
					}
				}
			} else if(sale.getTypeOfSale() == TypeOfSaleEnum.DINHEIRO) {
				Money money = (Money) sale;
				if(serviceHistoric.saveToHistory(money) == null) {
					return null;
				} else {
					removeProductsFromInventory();
					cart.clear();
					return serviceHistoric.getHistoricId(money.getId());
				}
			} else if(sale.getTypeOfSale() == TypeOfSaleEnum.PIX) {
				Pix pix = (Pix) sale;
				if(pix.getPixKey() == null || pix.getPixKey() == "") {
					return null;
				} else {
					if(serviceHistoric.saveToHistory(pix) == null) {
						return null;
					} else {
						removeProductsFromInventory();
						cart.clear();
						return serviceHistoric.getHistoricId(pix.getId());
					}
				}
			} else {
				return null;
			}
		}
	}
}
