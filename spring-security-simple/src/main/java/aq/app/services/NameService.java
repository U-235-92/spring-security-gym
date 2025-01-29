package aq.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NameService {

	private Map<String, List<String>> secretNames = Map.of(
			"alexander", List.of("Energico", "Perfecto"), 
			"alice", List.of("Fantastico"));

	@PreAuthorize("#name == authentication.name")
	public List<String> getSecretNames(String name) {
		return secretNames.get(name);
	}
	
	@PreAuthorize("hasAuthority('write')")
	public String getName() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		String username = securityContext.getAuthentication().getName();
		return username;
	}
}
