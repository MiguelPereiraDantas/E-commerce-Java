package br.com.fourstore.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import br.com.fourstore.enums.TypeOfSaleEnum;

@Document
public class Pix extends Sale{
	
	@NonNull
	private String pixKey;

	
	public String getPixKey() {
		return pixKey;
	}
	public void setPixKey(String pixKey) {
		this.pixKey = pixKey;
	}
	
	
	public Pix(String cpf, Double total, TypeOfSaleEnum typeOfSale, List<Product> cart, String pixKey) {
		super();
		this.setCpf(cpf);
		this.setTotal(total);
		this.setTypeOfSale(typeOfSale);
		this.setCart(cart);
		this.pixKey = pixKey;
	}
	
	
	@Override
	public String toString() {
		return "CPF: " + getCpf() + "\nData e Hora da Compra: " + getDate() + "\t" + getTime() 
			   + "\nForma de Pagamento: Pix" + "\nTotal das compras: R$" + getTotal()
			   + "\nChave Pix: " + pixKey;
	}
	
}
