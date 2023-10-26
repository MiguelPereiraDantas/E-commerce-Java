package br.com.fourstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum ColorEnum {
	PRETO(1),
	BRANCO(2),
	AZUL(3),
	VERMELHO(4),
	AMARELO(5),
	VERDE(6),
	LARANJA(7),
	VIOLETA(8);
	
	private Integer code;
	private static final Map<Integer, ColorEnum>getColor = new HashMap<>();
	
	ColorEnum(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	static {
		for(ColorEnum color: ColorEnum.values()) {
			getColor.put(color.getCode(), color);
		}
	}
	
	public static ColorEnum getColorEnum(Integer code) {
		return getColor.get(code);
	}
}
