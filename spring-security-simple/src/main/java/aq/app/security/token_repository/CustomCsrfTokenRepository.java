package aq.app.security.token_repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import aq.app.entities.CustomCsrfToken;
import aq.app.repositories.JpaCsrfTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

	private final JpaCsrfTokenRepository csrfTokenRepository;
	
	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString(); 
		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
	}

	@Override
	public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
		String identifier = request.getHeader("X-IDENTIFIER");
		Optional<CustomCsrfToken> existingCustomToken = csrfTokenRepository.findCustomCsrfTokenByIdentifier(identifier); 
		if(existingCustomToken.isPresent()) {
			CustomCsrfToken customToken = existingCustomToken.get();
			customToken.setToken(csrfToken.getToken());
			csrfTokenRepository.save(customToken);
		} else {
			CustomCsrfToken customToken = new CustomCsrfToken();
			customToken.setToken(csrfToken.getToken());
			customToken.setIdentifier(identifier);
			csrfTokenRepository.save(customToken);
		}
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		String identifier = request.getHeader("X-IDENTIFIER"); 
		Optional<CustomCsrfToken> existingCustomToken = csrfTokenRepository.findCustomCsrfTokenByIdentifier(identifier);
		if(existingCustomToken.isPresent()) {
			CustomCsrfToken customToken = existingCustomToken.get();
			return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", customToken.getToken());
		}
		return null;
	}
}
