package aq.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Value("${keySetURI}")
	private String keySetURI;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2ResourceServer(oauthCust -> oauthCust.jwt(jwtCust -> jwtCust.jwkSetUri(keySetURI)));
		http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
		http.csrf(customizer -> customizer.disable());
		return http.build();
	}
}
