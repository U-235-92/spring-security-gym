package aq.app.converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import aq.app.authentications.CustomJwtAuthenticationToken;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, CustomJwtAuthenticationToken> {

	@Override
	public CustomJwtAuthenticationToken convert(Jwt source) {
		List<GrantedAuthority> authorities = List.of(() -> "read");
		String priority = String.valueOf(source.getClaims().get("priority"));
		return new CustomJwtAuthenticationToken(source, authorities, priority);
	}
}
