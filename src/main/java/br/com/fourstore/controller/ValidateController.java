package br.com.fourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fourstore.service.ServiceValidate;

@RestController
@RequestMapping("/validate")
public class ValidateController {
	
	@Autowired
	ServiceValidate serviceValidate;
	
	@GetMapping("/cpf/{cpf}")
	public boolean getValidateCpf(@PathVariable("cpf") String cpf) {
		return serviceValidate.validateCpf(cpf);
	}
}
