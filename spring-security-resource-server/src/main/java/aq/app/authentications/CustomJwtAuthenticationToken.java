package aq.app.authentications;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@SuppressWarnings("serial")
public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {

	private final String priority;
	
	public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String priority) {
		super(jwt, authorities);
		this.priority = priority;
	}

	public String getPriority() {
		return priority;
	}
}
