package br.com.fourstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum SizeEnum {
	PP(1),
	P(2),
	M(3),
	G(4),
	GG(5),
	XGG(6);
	
	private Integer code;
	private static final Map<Integer, SizeEnum>getSize = new HashMap<>();
	
	SizeEnum(Integer code) {
		this.code = code;
	}
	
	public Integer getSize() {
		return code;
	}
	
	static {
		for(SizeEnum size: SizeEnum.values()) {
			getSize.put(size.getSize(), size);
		}
	}
	
	public static SizeEnum getSizeEnum(Integer code) {
		return getSize.get(code);
	}
}
