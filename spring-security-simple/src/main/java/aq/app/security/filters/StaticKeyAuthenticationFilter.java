package aq.app.security.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StaticKeyAuthenticationFilter implements Filter {

	private final String authenticationKey = "SD9cICjl1e";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String authentication = httpRequest.getHeader("Authentication");
		if(authentication.equals(authenticationKey))
			chain.doFilter(request, response);
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
