package aq.app.controllers;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {

	private final OAuth2AuthorizedClientManager clientManager;
	
	@GetMapping("/token")
	public String token() {
		OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
				.withClientRegistrationId("credentials-client")
				.principal("client")
				.build();        
		OAuth2AuthorizedClient client = clientManager.authorize(request);
		return client.getAccessToken().getTokenValue();
	}
}
