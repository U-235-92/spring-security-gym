package aq.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import aq.app.services.NameService;

@RestController
public class SecretNameController {

	@Autowired
	private NameService nameService;
	
	@GetMapping("/secret/names/{name}")
	public List<String> getSecretNames(@PathVariable String name) {
		return nameService.getSecretNames(name);
	}
}
