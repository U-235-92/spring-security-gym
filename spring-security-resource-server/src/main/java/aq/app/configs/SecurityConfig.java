package aq.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import aq.app.converters.JwtAuthenticationConverter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	@Value("${keySetURI}")
	private String keySetURI;
	@Value("${introspectionUri}")
	private String introspectionUri;
	@Value("${resourceserver.clientID}")
	private String resourceServerClientID;
	@Value("${resourceserver.secret}")
	private String resourceServerSecret;
	
	private final JwtAuthenticationConverter converter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.oauth2ResourceServer(oauthCust -> oauthCust
//				.jwt(jwtCust -> jwtCust
//					.jwkSetUri(keySetURI)
//					.jwtAuthenticationConverter(converter)));
		http.oauth2ResourceServer(oauthCust -> oauthCust
				.opaqueToken(tokenCust -> tokenCust
						.introspectionUri(introspectionUri)
						.introspectionClientCredentials(resourceServerClientID, resourceServerSecret)));
		http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
		http.csrf(customizer -> customizer.disable());
		return http.build();
	}
}
