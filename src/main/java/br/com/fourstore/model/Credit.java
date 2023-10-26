package br.com.fourstore.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import br.com.fourstore.enums.TypeOfSaleEnum;

@Document
public class Credit extends Sale{
	
	@NonNull
	private String cardNumber;
	
	@NonNull
	private String expirationDate;
	
	@NonNull
	private Integer securityCode;
	
	@NonNull
	private Integer installments;

	
	public Credit(String cpf, Double total, TypeOfSaleEnum typeOfSale, List<Product> cart, String cardNumber, String expirationDate, Integer securityCode, Integer installments) {
		super();
		this.setCpf(cpf);
		this.setTotal(total);
		this.setTypeOfSale(typeOfSale);
		this.setCart(cart);
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.installments = installments;
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
	
	public Integer getInstallments() {
		return installments;
	}
	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	
	@Override
	public String toString() {
		return "CPF: " + getCpf() + "\nData e Hora da Compra: " + getDate() + "\t" + getTime() 
			   + "\nForma de Pagamento: Credito" + "\nDados do Cartao: " + "\nNumero do cartao: " 
			   + cardNumber + "\tData de Vencimento: " + expirationDate + "\tCodigo de Seguranca: "
			   +  securityCode + "\nTotal das compras: R$" + getTotal() + "\tParcelas: " + installments;
	}
}
