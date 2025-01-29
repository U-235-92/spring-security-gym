package aq.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

	@GetMapping("/product/{code}")
	public String productCode(@PathVariable String code) {
		return code;
	}
}
