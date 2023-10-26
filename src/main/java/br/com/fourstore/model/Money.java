package br.com.fourstore.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fourstore.enums.TypeOfSaleEnum;


@Document
public class Money extends Sale{
	
	public Money(String cpf, Double total, TypeOfSaleEnum typeOfSale, List<Product> cart) {
		super();
		this.setCpf(cpf);
		this.setTotal(total);
		this.setTypeOfSale(typeOfSale);
		this.setCart(cart);
	}
	
	@Override
	public String toString() {
		return "CPF: " + getCpf() + "\nData e Hora da Compra: " + getDate() + "\t" + getTime() 
			   + "\nForma de Pagamento: Dinheiro" + "\nTotal das compras: R$" + getTotal();
	}
}
