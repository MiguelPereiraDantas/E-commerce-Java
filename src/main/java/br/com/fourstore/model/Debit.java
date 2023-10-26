package br.com.fourstore.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import br.com.fourstore.enums.TypeOfSaleEnum;

@Document
public class Debit extends Sale{
	
	@NonNull
	private String cardNumber;
	
	@NonNull
	private String expirationDate;
	
	@NonNull
	private Integer securityCode;
	
	
	public Debit(String cpf, Double total, TypeOfSaleEnum typeOfSale, List<Product> cart, String cardNumber, String expirationDate, Integer securityCode) {
		super();
		this.setCpf(cpf);
		this.setTotal(total);
		this.setTypeOfSale(typeOfSale);
		this.setCart(cart);
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public Integer getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}
	
	
	@Override
	public String toString() {
		return "CPF: " + getCpf() + "\nData e Hora da Compra: " + getDate() + "\t" + getTime() 
			   + "\nForma de Pagamento: Debito" + "\nDados do Cartao: " + "\nNumero do cartao: " 
			   + cardNumber + "\tData de Vencimento: " + expirationDate + "\tCodigo de Seguranca: "
			   +  securityCode + "\nTotal das compras: R$" + getTotal();
	}
}
