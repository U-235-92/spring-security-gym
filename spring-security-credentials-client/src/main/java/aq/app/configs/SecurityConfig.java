package aq.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2Login(customizer -> Customizer.withDefaults());
		http.authorizeHttpRequests(customizer -> customizer.anyRequest().permitAll());
		return http.build();
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration credentialsClientRegistration = ClientRegistration
				.withRegistrationId("credentials-client")
				.clientId("client")
				.clientSecret("secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope(OidcScopes.OPENID, "CUSTOM")
				.tokenUri("http://127.0.0.1:5080/oauth2/token")
				.build();
		InMemoryClientRegistrationRepository inMemoryClientclRegistrationRepository = 
				new InMemoryClientRegistrationRepository(credentialsClientRegistration);
		return inMemoryClientclRegistrationRepository;
	}
	
//	It uses to connect to an authorization server and right using of access token.
	@Bean
	OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {
		OAuth2AuthorizedClientProvider clientProviderBuilder = OAuth2AuthorizedClientProviderBuilder
				.builder()
				.clientCredentials()
				.build();
		DefaultOAuth2AuthorizedClientManager authorizedClientManager = 
				new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(clientProviderBuilder);
		return authorizedClientManager; 
	}
}
