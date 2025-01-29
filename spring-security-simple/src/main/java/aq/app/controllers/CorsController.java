package aq.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CorsController {

	@GetMapping("/") 
	public String main() {
		return "cors.html";
	}
	
	@ResponseBody
	@PostMapping("/test")
	@CrossOrigin("http://localhost:5085")
	public String test() {
		log.info("Test method called");
		return "HELLO";
	}
}
