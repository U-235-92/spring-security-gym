package aq.app.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ByeController {

//	Example of using transfer security context to another thread using SecurityContextHolder.setStrategyName adjusting
//	See AppConfig
	@Async
	@GetMapping("/bye")
	public void goodbye() throws InterruptedException, ExecutionException {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		String username = securityContext.getAuthentication().getName();
		log.info("Bye, " + username + "!");
	}
}
