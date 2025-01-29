package aq.app.security.users;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class User implements UserDetails {

	private final String username;
	private final String password;
	private final String authority;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
}
