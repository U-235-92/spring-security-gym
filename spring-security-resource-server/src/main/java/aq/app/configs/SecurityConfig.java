package aq.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

import aq.app.converters.JwtAuthenticationConverter;
import jakarta.servlet.http.HttpServletRequest;
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
//		http.oauth2ResourceServer(oauthCust -> oauthCust
//				.opaqueToken(tokenCust -> tokenCust
//						.introspectionUri(introspectionUri)
//						.introspectionClientCredentials(resourceServerClientID, resourceServerSecret)));
		http.oauth2ResourceServer(oauthCust -> oauthCust
				.authenticationManagerResolver(authenticationManagerResolver(jwtDecoder(), opaqueTokenIntrospector())));
		http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
		http.csrf(customizer -> customizer.disable());
		return http.build();
	}
	
	@Bean
	AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver(JwtDecoder jwtDecoder, OpaqueTokenIntrospector opaqueTokenIntrospector) {
		AuthenticationManager jwtAuth = new ProviderManager(new JwtAuthenticationProvider(jwtDecoder));
		AuthenticationManager opaqueAuth = new ProviderManager(new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector));
		return request -> {
			if("jwt".equals(request.getHeader("type")))
				return jwtAuth;
			else {
				return opaqueAuth;
			}
		};
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri("http://localhost:5080/oauth2/jwks").build();
	}

	@Bean
	OpaqueTokenIntrospector opaqueTokenIntrospector() {
		return new SpringOpaqueTokenIntrospector("http://localhost:5090/oauth2/introspect", "client", "secret");
	}
	
//	It uses when all the servers use the same token (like jwt or opaque)
//	@Bean
//	AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
//		String authServerOne = "http://localhost:5082";
//		String authServerTwo = "http://localhost:8052";
//		JwtIssuerAuthenticationManagerResolver resolver = JwtIssuerAuthenticationManagerResolver
//				.fromTrustedIssuers(authServerOne, authServerTwo);
//		return resolver;
//	}
}
