package aq.app.configs;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2Login(customizer -> Customizer.withDefaults());
		http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
		return http.build();
	}
	
	@Bean
	ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration authorizationClientRegistration = ClientRegistration
				.withRegistrationId(UUID.randomUUID().toString())
				.clientId("client")
				.clientSecret("secret")
				.clientName("Custom")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.scope(OidcScopes.OPENID)
				.redirectUri("http://localhost:5086/login/oauth2/code/custom-spring-security-oauth-client")
				.issuerUri("http://127.0.0.1:5082")
				.authorizationUri("http://127.0.0.1:5082/oauth2/authorize")
				.tokenUri("http://127.0.0.1:5082/oauth2/token")
				.jwkSetUri("http://127.0.0.1:5082/oauth2/jwks")
				.userInfoUri("http://127.0.0.1:5082/userinfo")
				.build();
		ClientRegistration googleClientRegistration = CommonOAuth2Provider
				.GOOGLE
				.getBuilder(UUID.randomUUID().toString())
				.clientId("")
				.clientSecret("")
				.scope(OidcScopes.OPENID)
				.redirectUri("http://localhost:5086/login/oauth2/code/google-spring-security-oauth-client")
				.build();
		InMemoryClientRegistrationRepository inMemoryClientclRegistrationRepository = 
				new InMemoryClientRegistrationRepository(
						authorizationClientRegistration, 
						googleClientRegistration);
		return inMemoryClientclRegistrationRepository;
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
