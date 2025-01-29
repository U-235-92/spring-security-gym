package aq.app.controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

//	Example of using transfer security context to another thread using DelegatingSecurityContextExecutorService
	@SuppressWarnings("resource")
	@GetMapping("/hola")
	public String hola() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};
		ExecutorService executor = Executors.newCachedThreadPool();
		DelegatingSecurityContextExecutorService delegatedExecutor = new DelegatingSecurityContextExecutorService(executor);
		try {
			return "Hola, " + delegatedExecutor.submit(task).get() + "!";
		} finally {
			delegatedExecutor.shutdown();
		}
	}
}
