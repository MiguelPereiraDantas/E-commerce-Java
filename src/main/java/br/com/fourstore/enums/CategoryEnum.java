package br.com.fourstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {
	CASUAL("RCA"),
	CLASSICA("RCL"),
	ESPORTIVA("RES"),
	SOCIAL("RSO");
	
	private String code;
	private static final Map<String, CategoryEnum>getCategory = new HashMap<>();
	
	CategoryEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	static {
		for(CategoryEnum category: CategoryEnum.values()) {
			getCategory.put(category.getCode(), category);
		}
	}
	
	public static CategoryEnum getCategoryEnum(String code) {
		return getCategory.get(code);
	}
}
