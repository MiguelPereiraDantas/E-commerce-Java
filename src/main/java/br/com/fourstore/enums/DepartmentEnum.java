package br.com.fourstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum DepartmentEnum {
	MASCULINO("MA"),
	FEMININO("FE"),
	INFANTIL("IN");
	
	private String code;
	private static final Map<String, DepartmentEnum>getDepartment = new HashMap<>();
	
	DepartmentEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	static {
		for(DepartmentEnum department: DepartmentEnum.values()) {
			getDepartment.put(department.getCode(), department);
		}
	}
	
	public static DepartmentEnum getDepartmentEnum(String code) {
		return getDepartment.get(code);
	}
}
