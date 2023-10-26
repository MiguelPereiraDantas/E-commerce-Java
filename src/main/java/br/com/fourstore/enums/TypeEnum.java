package br.com.fourstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeEnum {
	CAMISA("CAM"),
	CAMISETA("CAT"),
	CALCA("CAL"),
	SHORT("SHO"),
	BLUSA("BLU"),
	BONE("BON"),
	CHAPEU("CHA"),
	SAIA("SAI"),
	VESTIDO("VES");
	
	private String code;
	private static final Map<String, TypeEnum>getType = new HashMap<>();
	
	TypeEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	static {
		for(TypeEnum type: TypeEnum.values()) {
			getType.put(type.getCode(), type);
		}
	}
	
	public static TypeEnum getTypeEnum(String code) {
		return getType.get(code);
	}
}
