package aq.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import aq.app.services.NameService;

@RestController
public class HelloController {

	@Autowired
	private NameService nameService;
	
	@GetMapping("/hello")
	public String hello() {
		String username = nameService.getName();
		return "Hello, " + username + "!";
	}
	
	@GetMapping("/bonjour")
	public String bonjour() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		String username = securityContext.getAuthentication().getName();
		return "Bonjour, " + username + "!";
	}
	
	@GetMapping("/get-hello")
	public String getHello() {
		return "GET hello!";
	}
	
	@PostMapping("/post-hello")
	public String postHello() {
		return "POST hello!";
	}
}
