package br.com.fourstore.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import br.com.fourstore.enums.TypeOfSaleEnum;

@Document(collection = "historic")
public class Sale {
	
	@Transient //Anotação para não pegar o dateAndTime no bd
	Date dateAndTime = new Date();
	
	@Id
	private String id;
	
	private String cpf;
	
	private String date = new SimpleDateFormat("dd/MM/yyyy").format(dateAndTime);
	
	private String time = new SimpleDateFormat("HH:mm:ss").format(dateAndTime);
	
	@NonNull
	private Double total;
	
	@NonNull
	private TypeOfSaleEnum typeOfSale;
	
	@NonNull
	private List<Product> cart;
	
	
	public String getId() {
		return id;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public TypeOfSaleEnum getTypeOfSale() {
		return typeOfSale;
	}
	public void setTypeOfSale(TypeOfSaleEnum typeOfSale) {
		this.typeOfSale = typeOfSale;
	}

	public List<Product> getCart() {
		return cart;
	}
	public void setCart(List<Product> cart) {
		this.cart = cart;
	}
}
