package aq.app.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import aq.app.security.token_repository.CustomCsrfTokenRepository;

@Configuration
public class WebAuthorizationConfig {

	@Autowired
	@Qualifier("CustomAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;
	@SuppressWarnings("unused")
	@Autowired
	private CustomCsrfTokenRepository customCsrfTokenRepository;
	
	@Bean
	@SuppressWarnings("unused")
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		String authorityExpression = "hasAuthority('READ') and !hasAuthority('DELETE')";
		String roleExpression = "hasRole('ADMIN')";
		http
//			.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class)
//			.addFilterAt(new StaticKeyAuthenticationFilter(), BasicAuthenticationFilter.class)
//			.addFilterBefore(new RequestValidatioFilter(), BasicAuthenticationFilter.class)
//			.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
//			.authenticationProvider(authenticationProvider)
//			.authorizeHttpRequests(customizer -> customizer.anyRequest().access(new WebExpressionAuthorizationManager(roleExpression)))
//			.authorizeHttpRequests(customizer -> customizer.anyRequest().access(new WebExpressionAuthorizationManager(authorityExpression)))
//			.authorizeHttpRequests(customizer -> customizer.anyRequest().hasRole("ADMIN"))
//			.authorizeHttpRequests(customizer -> customizer.anyRequest().hasAnyAuthority("WRITE", "READ"))
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/hello").hasRole("ADMIN"))
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/bonjour").hasRole("MANAGER"))
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers(HttpMethod.GET, "/a").authenticated())
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers(HttpMethod.POST, "/a").permitAll())
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers("/product/{code:^[0-9]*$}").permitAll().anyRequest().denyAll())
//			.authorizeHttpRequests(customizer -> customizer.requestMatchers(RegexRequestMatcher.regexMatcher("/video/(us|uk|ca)+/(en|fr)*")).authenticated().anyRequest().hasAuthority("premium"))
			.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated())
//			.authorizeHttpRequests(customizer -> customizer.anyRequest().permitAll())
//			.authorizeHttpRequests(customizer -> customizer.anyRequest().denyAll())
//			.csrf(customizer -> customizer.disable())
//			.csrf(customizer -> customizer.ignoringRequestMatchers("/post-ciao"))
//			.csrf(customizer -> customizer.csrfTokenRepository(customCsrfTokenRepository).csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
//			.formLogin(customizer -> customizer.defaultSuccessUrl("/main", true))
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
