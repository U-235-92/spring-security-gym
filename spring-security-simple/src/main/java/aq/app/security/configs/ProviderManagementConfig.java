package aq.app.security.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import aq.app.security.providers.CustomAuthenticationProvider;

@Configuration
public class ProviderManagementConfig {

	@Bean
	@Qualifier("CustomAuthenticationProvider")
	AuthenticationProvider customAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		AuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider(userDetailsService, passwordEncoder);
		return customAuthenticationProvider;
	}
}
