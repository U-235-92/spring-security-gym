package aq.app.controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CiaoController {

//	Example of using transfer security context to another thread using DelegatingSecurityContextCallable
	@GetMapping("/ciao")
	public String ciao() throws InterruptedException, ExecutionException {
		Callable<String> task = () -> {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			String username = securityContext.getAuthentication().getName();
			return username;
		};
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			DelegatingSecurityContextCallable<String> contextTask = new DelegatingSecurityContextCallable<String>(task);
			String username = executor.submit(contextTask).get();
			return "Ciao, " + username + "!";
		} finally {
			executor.shutdown();
		}
	}
	
	@PostMapping("/post-ciao")
	public String postCiao() {
		return "POST ciao!";
	}
}
