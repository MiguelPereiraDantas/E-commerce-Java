package br.com.fourstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeOfSaleEnum {
	CREDITO(1),
	DEBITO(2),
	DINHEIRO(3),
	PIX(4);
	
	private Integer code;
	private static final Map<Integer, TypeOfSaleEnum>getTypeOfSale = new HashMap<>();
	
	TypeOfSaleEnum(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	static {
		for(TypeOfSaleEnum typeOfSale: TypeOfSaleEnum.values()) {
			getTypeOfSale.put(typeOfSale.getCode(), typeOfSale);
		}
	}
	
	public static TypeOfSaleEnum getTypeOfSaleEnum(Integer code) {
		return getTypeOfSale.get(code);
	}
}
