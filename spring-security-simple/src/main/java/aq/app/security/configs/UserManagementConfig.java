package aq.app.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {
	
//	Example of using spring's in memory user details service
//	@Bean
//	UserDetailsService inMemoryUserDetailsService() {
//		var user = User.withUsername("alice")
//				.password("123")
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
//	Example of using a custom user details service
//	@Bean
//	aq.app.security.services.InMemoryUserDetaisService customInMemoryUserDetailsService() {
//		UserDetails u = new aq.app.security.users.User("john", "12345", "read");
//		List<UserDetails> users = List.of(u);
//		return new aq.app.security.services.InMemoryUserDetaisService(users);
//	}
	
//	Example of using spring's jdbc user details service
//	@Bean
//	UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
//		String usersByUsernameQuery = "SELECT username, password, enabled FROM spring_users WHERE username = ?";
//		String authsByUsernameQuery = "SELECT username, authority FROM spring_authorities WHERE username = ?";
//		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		jdbcUserDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authsByUsernameQuery);
//		return jdbcUserDetailsManager;
//	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails alexander = User.builder()
				.username("alexander")
				.password("123")
//				.roles("manager")
//				.roles("ADMIN")
				.authorities("read")
				.build();
		UserDetails alice = User.builder()
				.username("alice")
				.password("123")
//				.roles("admin")
//				.roles("MANAGER")
//				.authorities("READ", "WRITE")
				.authorities("write")
				.build();
		UserDetails nikolai = User.builder()
				.username("nikolai")
				.password("123")
				.roles("manager")
//				.roles("ADMIN")
//				.authorities("read")
				.build();
		UserDetails julien = User.builder()
				.username("julien")
				.password("123")
				.roles("admin")
//				.roles("MANAGER")
//				.authorities("READ", "WRITE")
//				.authorities("write")
				.build();
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(alexander);
		inMemoryUserDetailsManager.createUser(alice);
		inMemoryUserDetailsManager.createUser(nikolai);
		inMemoryUserDetailsManager.createUser(julien);
		return inMemoryUserDetailsManager;
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
