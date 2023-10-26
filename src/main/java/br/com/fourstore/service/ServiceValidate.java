package br.com.fourstore.service;

import java.util.InputMismatchException;

import org.springframework.stereotype.Service;

@Service
public class ServiceValidate {
	
	public boolean validateCpf(String cpf) {
		char tenthDigit, eleventhDigit;
		int sum, result, number, weight;
		
		cpf = removeSpecialCharacters(cpf);
		
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || 
			cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || 
			cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || 
			cpf.equals("99999999999") || (cpf.length() != 11))
			return false;

		try {
			sum = 0;
			weight = 10;			
			for (int i = 0; i < 9; i++) {      
				number = (int) (cpf.charAt(i) - 48);
				sum = sum + (number * weight);
				weight = weight - 1;
			}

			result = 11 - (sum % 11);
			if ((result == 10) || (result == 11)) {
				tenthDigit = '0';
			} else {
				tenthDigit = (char) (result + 48);
			}				

			sum = 0;
			weight = 11;
			for (int i = 0; i < 10; i++) {
				number = (int) (cpf.charAt(i) - 48);
				sum = sum + (number * weight);
				weight = weight - 1;
			}

			result = 11 - (sum % 11);
			if ((result == 10) || (result == 11)) {
				eleventhDigit = '0';
			} else {
				eleventhDigit = (char) (result + 48);
			}
				
			if ((tenthDigit == cpf.charAt(9)) && (eleventhDigit == cpf.charAt(10))) {
				return true;
			} else {
				return false;
			}		
		} catch (InputMismatchException erro) {
			return false;
		}
	}
	
	private String removeSpecialCharacters(String cpf) {
		if (cpf.contains(".")) {
			cpf = cpf.replace(".", "");
		}
		if (cpf.contains("-")) {
			cpf = cpf.replace("-", "");
		}
		if (cpf.contains("/")) {
			cpf = cpf.replace("/", "");
		}
		return cpf;
	}
}
